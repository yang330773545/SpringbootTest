package com.vinzor.mysql.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinzor.mysql.entity.MyFile;
import com.vinzor.mysql.mapper.FileMapper;
import com.vinzor.mysql.service.MyFileService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MyFileServiceImpl implements MyFileService{
	
	@Autowired
	private FileMapper fileMapper;
	@Value("${file.upload-dir}")
	private String filePath;
	private ObjectMapper mapper=new ObjectMapper();
	
	@Override
	public MyFile findFileById(int id) {
		// TODO Auto-generated method stub
		return fileMapper.findFileById(id);
	}

	@Override
	public List<MyFile> findAllFile() {
		// TODO Auto-generated method stub
		return fileMapper.findAllFile();
	}

	@Override
	public boolean addFile(MultipartFile file,int type) {
		// TODO Auto-generated method stub
		if(file.isEmpty()) {
			log.error("传入文件为空");
			return false;
		}
		String names=file.getOriginalFilename();
		String name=names.substring(0, names.lastIndexOf("."));
		String fileAlias=UUID.randomUUID().toString()+names;
		File dest = new File(filePath+fileAlias);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();// 新建文件夹
        }
        try {
			file.transferTo(dest);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			log.error("文件上传出错 错误信息"+e.getMessage());
			return false;
		}
		
		return fileMapper.addFile(name, filePath+fileAlias,type)==1 ? true : false;
	}

	@Override
	public boolean deleteFile(int id) {
		// TODO Auto-generated method stub
		if(fileMapper.findFileById(id)==null) {
			log.error("删除文件接收的id在数据库中找不到");
			return false;
		}
		
		if(fileMapper.deleteFile(id)==1) {
			MyFile myFile=fileMapper.findFileById(id);
			try {
			File file = new File(myFile.getPath());
			file.delete();
			}catch (Exception e) {
				// TODO: handle exception			
				log.warn("删除指定文件路径出错");
				return true;
			}
			return true;
		}
		return false;
	}

	
	@Override
	public List<MyFile> findFileByType(int type) {
		// TODO Auto-generated method stub
		if(type==1 || type ==2) {
			return fileMapper.findFileByType(type);
		}
		log.warn("通过类型查找文件传入类型不为1或者2");
		return null;
	}

	@Override
	public List<MyFile> findMeetingFileByType(int meetingId) {
		// TODO Auto-generated method stub
		return fileMapper.findMeetingFileByType(meetingId);
	}

	@Override
	public List<MyFile> findSharedFileByType(int meetingId) {
		// TODO Auto-generated method stub
		return fileMapper.findSharedFileByType(meetingId);
	}

	@Transactional
	@Override
	public boolean addMeetingFile(HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        String meetingIds=request.getParameter("meetingId");
        int meetingId=Integer.parseInt(meetingIds);
		MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
            		String names=file.getOriginalFilename();
            		String name=names.substring(0, names.lastIndexOf("."));
            		String fileAlias=UUID.randomUUID().toString()+names;
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(filePath + fileAlias)));//设置文件路径及名字
                    stream.write(bytes);// 写入
                    stream.close();
                    fileMapper.addMeetingFile(name, filePath + fileAlias, meetingId);
                } catch (Exception e) {
                    stream = null;
                    log.error("第 " + i + " 个文件上传失败 ==> "+ e.getMessage());
                    return false;
                }
            } else {
            	log.error("第 " + i + " 个文件上传失败因为文件为空");
                return false;
            }
        }
		return true;
	}

	@Transactional
	@Override
	public boolean addSharedFile(HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        String meetingIds=request.getParameter("meetingId");
        int meetingId=Integer.parseInt(meetingIds);
        String participantIds=request.getParameter("participantId");
        int participantId=Integer.parseInt(participantIds);
		MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
            		String names=file.getOriginalFilename();
            		String name=names.substring(0, names.lastIndexOf("."));
            		String fileAlias=UUID.randomUUID().toString()+names;
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(filePath + fileAlias)));//设置文件路径及名字
                    stream.write(bytes);// 写入
                    stream.close();
                    fileMapper.addSharedFile(name, filePath + fileAlias, meetingId, participantId);
                } catch (Exception e) {
                    stream = null;
                    log.error("第 " + i + " 个文件上传失败 ==> "+ e.getMessage());
                    return false;
                }
            } else {
            	log.error("第 " + i + " 个文件上传失败因为文件为空");
                return false;
            }
        }
		return true;
	}

	
}
