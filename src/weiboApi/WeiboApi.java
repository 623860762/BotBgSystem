//package weiboApi;
//
//import weibo4j.org.json.*;
//import weibo4j.model.Tag;
//import weibo4j.model.User;
//
//import java.rmi.registry.LocateRegistry;
//import java.rmi.registry.Registry;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//
//import org.apache.log4j.Logger;
//
//import rmi.wbOpenApi.*;
//
//public class WeiboApi {
//	private static Logger rmilogger = Logger.getLogger(WeiboApi.class);
//	
//	private static WeibotOpenApiRmi woar = null;
//	private static Properties apiConn = null;
//	
//	public static boolean connect(String pConnStr){
//		String[] conns = pConnStr.split(",");
//		if(conns.length>=3){
//			Properties conn = new Properties();
//			conn.setProperty("ip", conns[0]);
//			conn.setProperty("port", conns[1]);
//			conn.setProperty("name", conns[2]);
//			return connect(conn);
//		}else{
//			return false;
//		}
//	}
//	public static boolean connect(Properties pConnProperties){
//		rmilogger.info("connect\tparameter\t"+pConnProperties.toString());
//		apiConn = pConnProperties;
//		
//		boolean succ = false;
//		String server_ip = pConnProperties.getProperty("ip");
//		String server_port = pConnProperties.getProperty("port");
//		String server_name = pConnProperties.getProperty("name");		
//		try{
//			Registry registry=LocateRegistry.getRegistry(server_ip, new Integer(server_port).intValue());
//			woar = (WeibotOpenApiRmi)registry.lookup(server_name);
//			succ = true;
//			rmilogger.info("connect\tconnnect_ok\t"+server_ip+":"+server_port+"/"+server_name);
//		}catch(Exception e){
//			rmilogger.info("connect\tconnnect_err\t"+server_ip+":"+server_port+"/"+server_name+"\t"+e.getLocalizedMessage());
//			e.printStackTrace();
//		}
//		return succ;
//	}
//	
//	public static boolean reconnect(){
//		if(apiConn!=null){
//			return connect(apiConn);
//		}else{
//			return false;
//		}
//	}
//
//	
//	public static User checkUserName(String pUserName){
//		pUserName = pUserName.replace("@", " ").trim();
//		rmilogger.info("checkUserName\tparameter\t"+pUserName);
//		User checkUser = null;
//		try {
//			checkUser = woar.getUserInfoByScreenName(pUserName);
//			if(checkUser!=null){
//				rmilogger.info("checkUserName\tok_1\t"+pUserName+"\t"+checkUser.getScreenName());
//			}else{
//				rmilogger.info("checkUserName\tok_1\t"+pUserName+"\tnull");
//			}			
//		} catch (Exception e) {
//			rmilogger.debug("checkUserName\terr_1\t"+pUserName+"\t"+e.getLocalizedMessage());
//			e.printStackTrace();
//			try {
//				reconnect();
//				checkUser = woar.getUserInfoByScreenName(pUserName);
//				if(checkUser!=null){
//					rmilogger.info("checkUserName\tok_2\t"+pUserName+"\t"+checkUser.getScreenName());
//				}else{
//					rmilogger.info("checkUserName\tok_2\t"+pUserName+"\tnull");
//				}			
//			} catch (Exception e2) {
//				rmilogger.debug("checkUserName\terr_2\t"+pUserName+"\t"+e.getLocalizedMessage());
//				e2.printStackTrace();
//			}
//		}
//		
//		if(checkUser!=null){
//			rmilogger.debug("checkUserName\treturn\t"+pUserName+"\t"+checkUser.toString());
//		}else{
//			rmilogger.debug("checkUserName\treturn\t"+pUserName+"\tnull");
//		}
//		
//		return checkUser;
//	}
//	
//	public static int checkRelationship(String pUidA, String pUidB){
//		rmilogger.info("checkRelationship\tparameter\t"+pUidA+"->"+pUidB);
//		int rtype=0;
//		try {
//			rtype = woar.userRelationship(pUidA, pUidB);
//			rmilogger.info("checkRelationship\tok_1\t"+pUidA+"->"+pUidB+":"+rtype);
//		} catch (Exception e) {
//			rmilogger.debug("checkRelationship\terr_1\t"+pUidA+"->"+pUidB+"\t"+e.getMessage());
//
//			try {
//				reconnect();
//				rtype = woar.userRelationship(pUidA, pUidB);
//				rmilogger.info("checkRelationship\tok_2\t"+pUidA+"->"+pUidB+":"+rtype);
//			} catch (Exception e2) {
//				rmilogger.debug("checkRelationship\terr_2\t"+pUidA+"->"+pUidB+"\t"+e2.getMessage());
//			}
//		}
//		
//		rmilogger.debug("checkRelationship\treturn\t"+pUidA+"->"+pUidB+":"+rtype);		
//		return rtype;
//	}
//	
//	public static List<Tag> getUserTags(String pUid){
//		rmilogger.info("getUserTags\tparameter\t"+pUid);
//		List<Tag> rTags = null;
//		try {
//			rTags = woar.getUserTagByID(pUid);
//			if(rTags!=null){
//				rmilogger.info("getUserTags\tok_1\t"+pUid+"\t"+rTags.toString());
//			}else{
//				rmilogger.info("getUserTags\tok_1\t"+pUid+"\tnull");
//			}			
//		} catch (Exception e) {
//			rmilogger.debug("getUserTags\terr_1\t"+pUid+"\t"+e.getMessage());
//
//			try {
//				reconnect();
//				rTags = woar.getUserTagByID(pUid);
//				if(rTags!=null){
//					rmilogger.info("getUserTags\tok_2\t"+pUid+"\t"+rTags.toString());
//				}else{
//					rmilogger.info("getUserTags\tok_2\t"+pUid+"\tnull");
//				}			
//			} catch (Exception e2) {
//				rmilogger.debug("getUserTags\terr_1\t"+pUid+"\t"+e2.getMessage());
//			}
//		}
//		rmilogger.debug("getUserTags\treturn\t"+pUid);		
//		return rTags;
//	}
//	
//}
