package com.itheima.es;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.TermVectorsResponse;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.client.ml.EvaluateDataFrameRequest;
import org.elasticsearch.cluster.metadata.MappingMetadata;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.xcontent.XContentBuilder;
import org.elasticsearch.xcontent.XContentType;
import org.elasticsearch.xcontent.json.JsonXContent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zy136
 * @date 2021/12/23 17:46
 */
public class EsDemo {

    private RestHighLevelClient client;

    @Before
    public void init() {
        client = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.115.128", 9200)));
    }

    @After
    public void destroy() throws IOException {
        client.close();
    }

    //fixme:操作索引库

    @Test//创建索引库
    public void testIndexCreate() throws IOException {
        CreateIndexResponse response = client.indices().create(new CreateIndexRequest("user"), RequestOptions.DEFAULT);
        if (response.isAcknowledged()) {
            System.out.println("创建成功");
        }
    }

    @Test//创建索引库 并添加属性 设置类型映射
    public void testIndexCreatePro() throws IOException {
        CreateIndexRequest user = new CreateIndexRequest("user");
        //索引库设置
        user.settings(Settings.builder().put("index.number_of_shards", 3).put("index.number_of_replicas", 1));
        //类型映射
        user.mapping(" {\n" +
                "    \"properties\": {\n" +
                "      \"id\": {\n" +
                "        \"type\": \"long\"\n" +
                "      },\n" +
                "      \"name\":{\n" +
                "        \"type\": \"keyword\"\n" +
                "      },\n" +
                "      \"age\":{\n" +
                "        \"type\": \"integer\"\n" +
                "      },\n" +
                "      \"gender\":{\n" +
                "        \"type\": \"keyword\"\n" +
                "      },\n" +
                "      \"note\":{\n" +
                "        \"type\": \"text\",\n" +
                "        \"analyzer\": \"ik_max_word\"\n" +
                "      }\n" +
                "    }\n" +
                "  }", XContentType.JSON);
        CreateIndexResponse response = client.indices().create(user, RequestOptions.DEFAULT);

        if (response.isAcknowledged()) {
            System.out.println("创建成功");
        }
    }


    @Test//查询索引库
    public void testGetIndex() throws IOException {
        GetIndexResponse response = client.indices().get(new GetIndexRequest("car"), RequestOptions.DEFAULT);
        Map<String, Settings> settings = response.getSettings();
        System.out.println("索引库的配置信息：" + settings);
        Map<String, MappingMetadata> mappings = response.getMappings();
        MappingMetadata car = mappings.get("car");
        System.out.println(car.source());
    }

    @Test//删除索引库
    public void testDeleteIndex() throws IOException {
        AcknowledgedResponse response = client.indices().delete(new DeleteIndexRequest("user"), RequestOptions.DEFAULT);
        if (response.isAcknowledged()) {
            System.out.println("删除成功");
        }
    }

    //fixme:操作文档

    @Test//指定id添加文档
    public void testAddDoc() throws IOException {
        IndexRequest indexRequest = new IndexRequest("user");
        indexRequest.id("1");
        indexRequest.source("{\n" +
                "    \"age\" : 18,\n" +
                "    \"gender\" :\"男\",\n" +
                "    \"name\" : \"大幂幂\",\n" +
                "    \"note\" : \"我好美啊.\"\n" +
                "}", XContentType.JSON);
        IndexResponse response = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(response.getResult());
    }

    @Test//指定id添加文档
    public void testAddDocPro() throws IOException {
        User user = new UserService().findById(11L);
        String userJson = JSON.toJSONString(user);
        System.out.println(userJson);

        IndexRequest indexRequest = new IndexRequest("user");
        indexRequest.id(user.getId().toString());
        indexRequest.source(userJson, XContentType.JSON);
        IndexResponse response = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(response.getResult());

    }

    @Test//指定id获取文档
    public void testGetDoc() throws IOException {
        GetRequest getRequest = new GetRequest("user", "11");
        GetResponse docs = client.get(getRequest, RequestOptions.DEFAULT);
        String userJson = docs.getSourceAsString();
        User user = JSON.parseObject(userJson, User.class);
        System.out.println(user);

    }

    @Test//指定id修改文档
    public void testUpdateDoc() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("user", "11");
        updateRequest.doc("gender", "女");
        UpdateResponse response = client.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(response.getResult());

    }

    @Test//指定id删除文档
    public void testDeleteDoc() throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest("user", "1");
        DeleteResponse response = client.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(response.getResult());

    }

    @Test//批量添加数据
    public void testBulkDoc() throws IOException {
        List<User> userList = new UserService().findAll();
        BulkRequest bulkRequest = new BulkRequest("user");
        for (User user : userList) {
            bulkRequest.add(new IndexRequest()
                    .id(user.getId().toString())
                    .source(JSON.toJSONString(user), XContentType.JSON));
        }
        BulkResponse response = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(response.status());

    }

    @Test//检索所有文档数据(match_all) 并排序 并分页每页展示4条数据
    public void testSearchMatchAllDoc() throws IOException {
        SearchRequest searchRequest = new SearchRequest("user");
        searchRequest.source(SearchSourceBuilder.searchSource()
                .query(QueryBuilders.matchAllQuery())
                .sort("id", SortOrder.ASC)
                .from(0).size(4));
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        for (SearchHit hit : hits.getHits()) {
            System.out.println(JSON.parseObject(hit.getSourceAsString(), User.class));
        }

    }

    @Test//分词查询 match
    public void testSearchMatchDoc() throws IOException {
        SearchRequest searchRequest = new SearchRequest("user");
        searchRequest.source(SearchSourceBuilder.searchSource()
                .query(QueryBuilders.matchQuery("note", "JAVA"))//查询的字段,查询的关键词
                .sort("id", SortOrder.ASC));
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        for (SearchHit hit : hits.getHits()) {
            System.out.println(JSON.parseObject(hit.getSourceAsString(), User.class));
        }

    }

    @Test//词条查询 term
    public void testSearchTermDoc() throws IOException {
        SearchRequest searchRequest = new SearchRequest("user");
        searchRequest.source(SearchSourceBuilder.searchSource()
                .query(QueryBuilders.termQuery("note", "唱歌")));//查询的字段,查询的关键词
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        for (SearchHit hit : hits.getHits()) {
            System.out.println(JSON.parseObject(hit.getSourceAsString(), User.class));
        }

    }

    @Test//布尔查询 bool
    public void testSearchBoolDoc() throws IOException {
        SearchRequest searchRequest = new SearchRequest("user");
        searchRequest.source(SearchSourceBuilder.searchSource()
                .query(QueryBuilders.boolQuery()//布尔查询
                        .must(QueryBuilders.matchQuery("note", "学"))//必须:match note中含学
                        .mustNot(QueryBuilders.rangeQuery("age").gte(25).lte(50))//必须不(不要): 范围查询 20-50 age年龄
                        .filter(QueryBuilders.matchQuery("note", "java"))));//把含java的过滤出来
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        for (SearchHit hit : hits.getHits()) {
            System.out.println(JSON.parseObject(hit.getSourceAsString(), User.class));
        }

    }

    @Test//source 指定查询显示哪些字段
    public void testSearchSourceDoc() throws IOException {
        SearchRequest searchRequest = new SearchRequest("user");
        searchRequest.source(SearchSourceBuilder.searchSource()
                .query(QueryBuilders.matchAllQuery()).fetchSource(new String[]{"name", "age", "gender"}, null));//包含字段,不包含字段//excludes:
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        for (SearchHit hit : hits.getHits()) {
            System.out.println(JSON.parseObject(hit.getSourceAsString(), User.class));
        }

    }

    @Test//highlight 高亮查询
    public void testSearchHighlightDoc() throws IOException {
        //构建查询主体 指定查询索引库
        SearchRequest searchRequest = new SearchRequest("user");
        //查询构建器
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //高亮构建器
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        //指定高亮字段，高亮类型
        highlightBuilder.field("note").highlighterType("unified");
        //添加查询构建器指定高亮,并指定查询条件
        searchSourceBuilder.highlighter(highlightBuilder).query(QueryBuilders.matchQuery("note", "java"));
        //查询构建起加入查询主体
        searchRequest.source(searchSourceBuilder);
        //执行查询
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        //处理结果
        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getHighlightFields());
        }

    }

    @Test//aggs 聚合分桶
    public void testSearchAggsDoc() throws IOException {
        SearchRequest searchRequest = new SearchRequest("user");
        searchRequest.source(SearchSourceBuilder.searchSource()
                .query(QueryBuilders.matchAllQuery())
                .aggregation(AggregationBuilders
                        .terms("gender_agg").field("gender")));
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        Aggregations aggregations = response.getAggregations();
        Terms gender_agg = aggregations.get("gender_agg");
        List<? extends Terms.Bucket> buckets = gender_agg.getBuckets();
        for (Terms.Bucket bucket : buckets) {
            System.out.println(bucket.getKeyAsString() + "数量:" + bucket.getDocCount());
        }

    }


}
