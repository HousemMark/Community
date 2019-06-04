package com.xq.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

@Configuration
@PropertySource(value="classpath:/properties/redis.properties")
public class RedisConfig {

	//	4.redis集群配置
	@Value("${redis.clusterNodes}")
	private String clusterNodes;

	@Bean
	public JedisCluster getCluster() {
		HashSet<HostAndPort> nodes = new HashSet<>();
		String[] nodeList = clusterNodes.split(",");
		for (String node : nodeList) {
			String[] ip = node.split(":");
			String host = ip[0];
			int port = Integer.parseInt(ip[1]);
			nodes.add(new HostAndPort(host, port));
		}
		return new JedisCluster(nodes);
	}
	//1.redis分片配置
	//	@Value("${redis.nodes}")
	//	private String nodes;

	//	@Bean
	//	public ShardedJedis getShards() {
	//		List<JedisShardInfo> shards = new ArrayList<>();
	//		String[] nodeList = nodes.split(",");
	//		for (String node : nodeList) {
	//			String[] args = node.split(":");
	//			String host = args[0];
	//			int port = Integer.parseInt(args[1]);
	//			JedisShardInfo info = new JedisShardInfo(host, port);
	//			shards.add(info);
	//		}
	//		return new ShardedJedis(shards);
	//	}
	//	
	//2.redis单台配置
	@Value("${redis.host}")
	private String host;
	@Value("${redis.port}")
	private int port;

	@Bean		//执行方法获取实例化对象
	public Jedis getJedis() {
		return new Jedis(host,port);
	}

	//	//3.redis哨兵配置
	//	@Value("${redis.masteName}")
	//	private String masterName;
	//	@Value("${redis.sentinelNodes}")
	//	private String sentinelNodes;

	//	@Bean
	//	public JedisSentinelPool getPool() {
	//		HashSet<String> sentinels = new HashSet<>();
	//		String[] nodeList = sentinelNodes.split(",");
	//		for (String node : nodeList) {
	//			sentinels.add(node);
	//		}
	//		return new JedisSentinelPool(masterName, sentinels);
	//	}
}
