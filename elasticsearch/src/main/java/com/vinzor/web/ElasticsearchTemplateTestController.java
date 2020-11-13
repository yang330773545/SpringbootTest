package com.vinzor.web;

import com.vinzor.po.Teacher;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.*;

@RestController
public class ElasticsearchTemplateTestController {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * Pageable定义了很多方法，但其核心的信息只有两个：一是分页的信息（page、size），二是排序的信息 Spring会自动的根据request的参数来组装该pageable对象
     * @PageableDefault帮助我们个性化的设置pageable的默认配置 例如@PageableDefault(value = 15, sort = { "id" }, direction = Sort.Direction.DESC)表示默认情况下我们按照id倒序排列，每一页的大小为15。
     *
     * 单字符串模糊查询，默认排序。将从所有字段中查找包含传来的word分词后字符串的数据集
     */
    @RequestMapping("/singleWord")
    public List<Teacher> singleTitle(String name, @PageableDefault Pageable pageable) {
        //使用queryStringQuery完成单字符串查询
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.queryStringQuery(name)).withPageable(pageable).build();
        return elasticsearchTemplate.queryForList(searchQuery, Teacher.class);
    }

    /**
     * 同上个方法类似 查询某个字段中模糊包含目标字符串
     */
    @RequestMapping("/singleMatch")
    public Object singleMatch(String name, @PageableDefault Pageable pageable) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchQuery("name", name)).withPageable(pageable).build();
        return elasticsearchTemplate.queryForList(searchQuery, Teacher.class);
    }

    /**
     * 只保留包含了所有搜索词条的文档，并且词条的位置要邻接 类似于 %XXX%
     */
    @RequestMapping("/singlePhraseMatch")
    public Object singlePhraseMatch(String name, @PageableDefault Pageable pageable) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchPhraseQuery("name", name)).withPageable(pageable).build();
        return elasticsearchTemplate.queryForList(searchQuery, Teacher.class);
    }

    /**
     * 精确匹配  相当于 ==
     */
    @RequestMapping("/singleTerm")
    public Object singleTerm(Integer userId, @PageableDefault Pageable pageable) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(termQuery("id", userId)).withPageable(pageable).build();
        return elasticsearchTemplate.queryForList(searchQuery, Teacher.class);
    }

    /**
     * 匹配多个文档
     */
    @RequestMapping("/multiMatch")
    public Object singleUserId(String title, @PageableDefault(sort = "weight", direction = Sort.Direction.DESC) Pageable pageable) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.multiMatchQuery(title, "name", "school")).withPageable(pageable).build();
        return elasticsearchTemplate.queryForList(searchQuery, Teacher.class);
    }

    @RequestMapping("/contain")
    public Object contain(String title) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchQuery("title", title).operator(Operator.AND)).build();
        return elasticsearchTemplate.queryForList(searchQuery, Teacher.class);
    }

    @RequestMapping("/bool")
    public Object bool(String title, Integer userId, Integer weight) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.boolQuery().must(termQuery("userId", userId))
                .should(rangeQuery("weight").lt(weight)).must(matchQuery("title", title))).build();
        return elasticsearchTemplate.queryForList(searchQuery, Teacher.class);
    }
}
