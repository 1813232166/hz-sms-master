package com.hzwealth.sms.common.utils.token;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.SysexMessage;

import org.apache.log4j.Logger;

import com.hzwealth.sms.common.utils.redis.RedisCacheDao;

    /**
     * 
      * @ClassName: TokenHelper 
      * @Description: Token校验工具类，拦截请求校验Token
      * @author chenmingqiang
      * @date 2017年4月19日 下午1:46:39 
      *
     */
    public class TokenHelper
    {

        /**
         * 保存token值的默认命名空间
         */
        public static final String TOKEN_NAMESPACE = "hzcf.tokens";

        /**
         * 持有token名称的字段名
         */
        public static final String TOKEN_NAME_FIELD = "hzcf.token.name";
        private static final Logger LOG = Logger.getLogger(TokenHelper.class);
        private static final Random RANDOM = new Random();

        /**
         * 使用随机字串作为token名字保存token
         *
         * @param request
         * @return token
         */
        public static String setToken(HttpServletRequest request)
        {
            return setToken(request, generateGUID());
        }

        /**
         * 使用给定的字串作为token名字保存token
         *
         * @param request
         * @param tokenName
         * @return token
         */
        private static String setToken(HttpServletRequest request, String tokenName)
        {
            String token = generateGUID();
            setCacheToken(request, tokenName, token);
            return token;
        }

        /**
         * 保存一个给定名字和值的token
         *
         * @param request
         * @param tokenName
         * @param token
         */
        private static void setCacheToken(HttpServletRequest request, String tokenName, String token)
        {
            try
            {
                String tokenName0 = buildTokenCacheAttributeName(tokenName);
                RedisCacheDao.set(tokenName0, token,1800);
                LOG.info("r_tokenName0------"+tokenName0);
                LOG.info("r_token------"+token);
                Map<String,String> map = new HashMap<>();
                map.put("tokenName",tokenName0);
                map.put("token", token);
                request.setAttribute("map",map);
            }
            catch(IllegalStateException e)
            {
                String msg = "Error creating HttpSession due response is commited to client. You can use the CreateSessionInterceptor or create the HttpSession from your action before the result is rendered to the client: " + e.getMessage();
                LOG.error(msg, e);
                throw new IllegalArgumentException(msg);
            }
        }

        /**
         * 构建一个基于token名字的带有命名空间为前缀的token名字
         *
         * @param tokenName
         * @return the name space prefixed session token name
         */
        public static String buildTokenCacheAttributeName(String tokenName)
        {
            return TOKEN_NAMESPACE + "." + tokenName;
        }

        /**
         * 从请求域中获取给定token名字的token值
         *
         * @param tokenName
         * @return the token String or null, if the token could not be found
         */
        public static String getToken(HttpServletRequest request, String tokenName)
        {
            if(tokenName == null)
            {
                return null;
            }
            Map params = request.getParameterMap();
            String[] tokens = (String[]) params.get(tokenName);
            String token;
            if((tokens == null) || (tokens.length < 1))
            {
                LOG.warn("Could not find token mapped to token name " + tokenName);
                return null;
            }

            token = tokens[0];
            return token;
        }

        /**
         * 从请求参数中获取token名字
         *
         * @return the token name found in the params, or null if it could not be found
         */
        public static String getTokenName(HttpServletRequest request)
        {
            Map params = request.getParameterMap();

            if(!params.containsKey(TOKEN_NAME_FIELD))
            {
                LOG.warn("Could not find token name in params.");
                return null;
            }

            String[] tokenNames = (String[]) params.get(TOKEN_NAME_FIELD);
            String tokenName;

            if((tokenNames == null) || (tokenNames.length < 1))
            {
                LOG.warn("Got a null or empty token name.");
                return null;
            }

            tokenName = tokenNames[0];

            return tokenName;
        }

        /**
         * 验证当前请求参数中的token是否合法，如果合法的token出现就会删除它，它不会再次成功合法的token
         *
         * @return 验证结果
         */
        public static boolean validToken(HttpServletRequest request)
        {
            String tokenName = getTokenName(request);

            if(tokenName == null)
            {
                LOG.debug("no token name found -> Invalid token ");
                return false;
            }

            String token = getToken(request, tokenName);

            if(token == null)
            {
                if(LOG.isDebugEnabled())
                {
                    LOG.debug("no token found for token name " + tokenName + " -> Invalid token ");
                }
                return false;
            }

//            String tokenCacheName = buildTokenCacheAttributeName(tokenName);

            String cacheToken = (String)RedisCacheDao.read(tokenName);
            LOG.info("rtn_r_tokenName0------"+tokenName);
            LOG.info("rtn_r_token------"+cacheToken);
            LOG.info("token------"+token);
            if(!token.equals(cacheToken))
            {
                LOG.warn("xxx.internal.invalid.token Form token " + token + " does not match the session token " + cacheToken + ".");
                return false;
            }

            // remove the token so it won't be used again
            boolean istrue = RedisCacheDao.delete(tokenName);
            System.err.println("redis删除结果："+istrue);
            return true;
        }

        public static String generateGUID()
        {
            return new BigInteger(165, RANDOM).toString(36).toUpperCase();
        }
        /**
         * 刷新缓存中的Token信息
         * @param request HttpServletRequest
         * @return
         */
        public static Boolean refreshCacheToken(HttpServletRequest request) {
        	String tokenName = getTokenName(request);
        	String tokenValue = getToken(request, tokenName);
        	
        	return RedisCacheDao.set(tokenName, tokenValue,1800);
        }

    }

