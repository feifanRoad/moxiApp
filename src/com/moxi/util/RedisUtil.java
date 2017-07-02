package com.moxi.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public final class RedisUtil {
	// Redis������IP

	private static String ADDR = "47.52.33.131"; // Redis�Ķ˿ں�

	private static int PORT = 6379; // ��������

	private static String AUTH = "admin"; // ��������ʵ��������Ŀ��Ĭ��ֵΪ8��
	// ���ֵΪ-1�����ʾ�����ƣ����pool�Ѿ�������maxActive��jedisʵ�����ʱpool��״̬Ϊexhausted(�ľ�)��

	private static int MAX_ACTIVE = 1024; // ����һ��pool����ж��ٸ�״̬Ϊidle(���е�)��jedisʵ��Ĭ��ֵҲ��8��

	private static int MAX_IDLE = 200; // �ȴ�������ӵ����ʱ�䣬��λ���룬Ĭ��ֵΪ-1����ʾ������ʱ������ȴ�ʱ�䣬��ֱ���׳�JedisConnectionException��

	private static int MAX_WAIT = 10000;
	private static int TIMEOUT = 10000; // ��borrowһ��jedisʵ��ʱ���Ƿ���ǰ����validate���������Ϊtrue����õ���jedisʵ����ǿ��õģ�

	private static boolean TEST_ON_BORROW = true;
	private static JedisPool jedisPool = null;

	/**
	 * 36 * ��ʼ��Redis���ӳ� 37
	 */

	static {
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(MAX_ACTIVE);
			config.setMaxIdle(MAX_IDLE);
			config.setMaxWaitMillis(MAX_WAIT);
			config.setTestOnBorrow(TEST_ON_BORROW);
			jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 52 * ��ȡJedisʵ�� 53 * @return 54
	 */

	public synchronized static Jedis getJedis() {
		try {
			if (jedisPool != null) {
				Jedis resource = jedisPool.getResource();
				return resource;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void returnResource(final Jedis jedis) {
		if (jedis != null) {
			jedisPool.returnResource(jedis);
		}
	}

	public static void main(String[] args) {
		Jedis tool=getJedis();
//		tool.set("asat", "27");
	    String age=tool.get("asat");
	    System.out.println(age);
//	    tool.eval(script, keys, args)

	}

}
