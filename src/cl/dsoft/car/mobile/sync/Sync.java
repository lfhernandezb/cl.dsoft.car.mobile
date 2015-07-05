/**
 * 
 */
package cl.dsoft.car.mobile.sync;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import cl.dsoft.car.misc.UnsupportedParameterException;
import cl.dsoft.car.mobile.db.InfoSincro;
import cl.dsoft.car.mobile.db.Usuario;
import cl.dsoft.car.mobile.model.CarData;

/**
 * @author lfhernandez
 *
 */
public class Sync {

	/**
	 * 
	 */
	public Sync() {
		// TODO Auto-generated constructor stub
	}
	
	public static SyncData ByIdUsuario(Connection conn, String strServer, long idUsuario, String strLastSyncDate) throws UnsupportedEncodingException, UnsupportedParameterException, SQLException, ClientProtocolException, IOException, Exception {
		
    	int timeoutConnection;
    	int timeoutSocket;
    	HttpGet getRequest;
        HttpClient httpClient;
        HttpResponse response;
    	StatusLine statusLine;
    	SyncData syncData;
    	
    	syncData = new SyncData();
    	
        String serverURL = strServer + "byIdUsuario/";
    	
        HttpParams httpParameters = new BasicHttpParams();
        
        timeoutConnection = 10000;
        HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
        timeoutSocket = 20000;
        HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
        httpClient = new DefaultHttpClient(httpParameters);
        
        //getRequest = new HttpGet(serverURL + serverText.getText());
        getRequest = new HttpGet(serverURL + String.valueOf(idUsuario) + "/" + URLEncoder.encode(strLastSyncDate, "UTF-8"));
        
        getRequest.addHeader("accept", "application/xml");      	
    	
    	response = httpClient.execute(getRequest);
        statusLine = response.getStatusLine();
        
        if(statusLine.getStatusCode() == HttpStatus.SC_OK){
        	
        	Serializer serializer;
        	//CarData carData;
        	        	
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            response.getEntity().writeTo(out);
            out.close();
            syncData.setStrResponse(out.toString());
                        
            serializer = new Persister();
            
			syncData.setCarData(serializer.read(CarData.class, out.toString()));
			
			
			
			//jsonParsed.setText(carData.getUsuarios().toString());
			
			//System.out.println(carData.getUsuarios());
				        				        			
			/*	        	    	
	    	if (!carData.getVwCampaniaUsuarios().isEmpty()) {
	    		VwCampaniaUsuario cu = carData.getVwCampaniaUsuarios().get(0);
	    		
	    		JSONObject jo = new JSONObject(cu.getDetalle());
	    		
	    		System.out.println(jo.toString());
	    	}
    	    */			        	    
    	    
    	    			                
        } else{
            //Closes the connection.
            response.getEntity().getContent().close();
            throw new IOException(statusLine.getReasonPhrase());
        }
        
        return syncData;
	}

	public static SyncData ByIdRedSocial(Connection conn, String strServer, long idRedSocial, String token) throws UnsupportedParameterException, SQLException, ClientProtocolException, IOException, Exception {
		
    	int timeoutConnection;
    	int timeoutSocket;
    	HttpGet getRequest;
        HttpClient httpClient;
        HttpResponse response;
    	StatusLine statusLine;
    	SyncData syncData;
    	
    	syncData = new SyncData();
        
        String serverURL = strServer + "byIdRedSocial/";
    	
        HttpParams httpParameters = new BasicHttpParams();
        
        timeoutConnection = 10000;
        HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
        timeoutSocket = 20000;
        HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
        httpClient = new DefaultHttpClient(httpParameters);
        
        //getRequest = new HttpGet(serverURL + serverText.getText());
        getRequest = new HttpGet(serverURL + String.valueOf(idRedSocial) + "/" + token);
        
        getRequest.addHeader("accept", "application/xml");      	
    	
    	response = httpClient.execute(getRequest);
        statusLine = response.getStatusLine();
        
        if(statusLine.getStatusCode() == HttpStatus.SC_OK){
        	
        	Serializer serializer;
        	//CarData carData;
        	
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            response.getEntity().writeTo(out);
            out.close();
            syncData.setStrResponse(out.toString());
            
            serializer = new Persister();
            
            syncData.setCarData(serializer.read(CarData.class, out.toString()));
			
			
			
        } else{
            //Closes the connection.
            response.getEntity().getContent().close();
            throw new IOException(statusLine.getReasonPhrase());
        }
        
        return syncData;
	}
	
	public static SyncData CreateUser(Connection conn, String strServer, long idRedSocial, String token, long idComuna) throws UnsupportedParameterException, SQLException, ClientProtocolException, IOException, Exception {
		
    	int timeoutConnection;
    	int timeoutSocket;
    	HttpGet getRequest;
        HttpClient httpClient;
        HttpResponse response;
    	StatusLine statusLine;
    	SyncData syncData;
    	
    	syncData = new SyncData();
        
        String serverURL = strServer + "createUser/";
    	
        HttpParams httpParameters = new BasicHttpParams();
        
        timeoutConnection = 10000;
        HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
        timeoutSocket = 20000;
        HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
        httpClient = new DefaultHttpClient(httpParameters);
        
        //getRequest = new HttpGet(serverURL + serverText.getText());
        getRequest = new HttpGet(serverURL + String.valueOf(idRedSocial) + "/" + token + "/" + String.valueOf(idComuna));
        
        getRequest.addHeader("accept", "application/xml");      	
    	
    	response = httpClient.execute(getRequest);
        statusLine = response.getStatusLine();
        
        if(statusLine.getStatusCode() == HttpStatus.SC_OK){
        	
        	Serializer serializer;
        	//CarData carData;
        	
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            response.getEntity().writeTo(out);
            out.close();
            syncData.setStrResponse(out.toString());
            
            serializer = new Persister();
            
            syncData.setCarData(serializer.read(CarData.class, out.toString()));
			
			//jsonParsed.setText(carData.getUsuarios().toString());
			
			//System.out.println(carData.getUsuarios());
			    	    
        } else{
            //Closes the connection.
            response.getEntity().getContent().close();
            throw new IOException(statusLine.getReasonPhrase());
        }
        
        return syncData;
	}

	public static SyncData PutData(Connection conn, String strServer, long idUsuario, String strLastSyncDate) throws ClientProtocolException, IOException, Exception {
		
    	SyncData syncData;
    	
    	syncData = new SyncData();
		
		String serverURL = strServer + "receive";
		
		syncData.setCarData(new CarData(conn, idUsuario, strLastSyncDate));
		
		if (syncData.getCarData().hasData()) {
			
        	int timeoutConnection;
        	int timeoutSocket;
        	HttpPut putRequest;
	        HttpClient httpClient;
	        HttpResponse response;
        	StatusLine statusLine;
        	Serializer serializer;
        	StringWriter stringWriter;
        	StringEntity entity;
        	
        	String url;
        	
        	HttpParams httpParameters = new BasicHttpParams();
            timeoutConnection = 10000;
            HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
            timeoutSocket = 20000;
            HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
            httpClient = new DefaultHttpClient(httpParameters);
            putRequest = new HttpPut(serverURL);
            putRequest.addHeader("content-type", "application/xml");

            serializer = new Persister();
            
            stringWriter = new StringWriter();
            
            serializer.write(syncData.getCarData(), stringWriter);
            
            entity = new StringEntity("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>" + stringWriter.toString(), HTTP.UTF_8);
            putRequest.setEntity(entity);
        	
        	response = httpClient.execute(putRequest);
            statusLine = response.getStatusLine();
            
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
            	
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                out.close();
                syncData.setStrResponse(out.toString());
                
            } else{
                //Closes the connection.
            	response.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
            }
		}
		
		return syncData;
	}
}
