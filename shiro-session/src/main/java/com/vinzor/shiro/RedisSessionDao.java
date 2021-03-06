package com.vinzor.shiro;


import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;



public class RedisSessionDao extends AbstractSessionDAO {
	 private static Logger logger = LoggerFactory.getLogger(RedisSessionDao.class);

	 	@Autowired
	 	private RedisTemplate<String, Object> redisTemplate;
	    // 0 - never expire
	    private int expire = 3600000;
	    
	    
	    /**
	     * The Redis key prefix for the sessions 
	     */
	    private String keyPrefix = "shiro_redis_session:";
	    
	    @Override
	    public void update(Session session) throws UnknownSessionException {
	        this.saveSession(session);
	    }
	    
	    /**
	     * save session
	     * @param session
	     * @throws UnknownSessionException
	     */
	    private void saveSession(Session session) throws UnknownSessionException{
	        if(session == null || session.getId() == null){
	            logger.error("session or session id is null");
	            return;
	        }
	        String key = session.getId().toString();
	        session.setTimeout(expire);    	        
	        redisTemplate.opsForValue().set(keyPrefix+key, session, expire, TimeUnit.MILLISECONDS);
	    }

	    @Override
	    public void delete(Session session) {
	        if(session == null || session.getId() == null){
	            logger.error("session or session id is null");
	            return;
	        }
	        redisTemplate.delete(keyPrefix+session.getId().toString());

	    }

	    @Override
	    public Collection<Session> getActiveSessions() {
	        Set<Session> sessions = new HashSet<Session>();
	        Set<String> keys = redisTemplate.keys(this.keyPrefix + "*");
	        if(keys != null && keys.size()>0){
	            for(String key:keys){
	                Session s = (Session)redisTemplate.opsForValue().get(key);
	                sessions.add(s);
	            }
	        }
	        
	        return sessions;
	    }

	    @Override
	    protected Serializable doCreate(Session session) {
	        Serializable sessionId = this.generateSessionId(session);  
	        this.assignSessionId(session, sessionId);
	        this.saveSession(session);
	        return sessionId;
	    }

	    @Override
	    protected Session doReadSession(Serializable sessionId) {
	        if(sessionId == null){
	            logger.error("session id is null");
	            return null;
	        }
	        Session s = (Session)redisTemplate.opsForValue().get(keyPrefix+sessionId);
	        
	        return s;
	    }
	    
	    /**
	     * Returns the Redis session keys
	     * prefix.
	     * @return The prefix
	     */
	    public String getKeyPrefix() {
	        return keyPrefix;
	    }

	    /**
	     * Sets the Redis sessions key 
	     * prefix.
	     * @param keyPrefix The prefix
	     */
	    public void setKeyPrefix(String keyPrefix) {
	        this.keyPrefix = keyPrefix;
	    }

	    public RedisTemplate<String, Object> getRedisTemplate() {
	        return redisTemplate;
	    }

	    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
	        this.redisTemplate = redisTemplate;
	    }
	    

}
