package Services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import Tools.MessageTools;
import Tools.MongoTools;
import Tools.ServiceTools;

public class Message {
	public static JSONObject addMessage(String myLogin,String key, String Message)
	{
		
		JSONObject obj = null;
		if(myLogin == null || key == null || Message == null)
		{
			obj = Tools.ServiceTools.refused(3);
			
		}
		else if(!Tools.UserTools.existsUser(myLogin))
		{
			obj = Tools.ServiceTools.refused(4);
		}
		else if(!Tools.UserTools.keyLogin(myLogin, key))
		{
			obj = Tools.ServiceTools.refused(7);
		}
		else
		{
			// recuperer l'id
			int id = ServiceTools.getID(myLogin);
			// ajouter dans la base de données le message 
			GregorianCalendar cal = new java.util.GregorianCalendar();
			cal.add(Calendar.HOUR, 0);
			Date d = cal.getTime();
			System.out.println(d);
			MessageTools.addMessage(id, myLogin, Message, d);
			try {
				obj = new JSONObject();
				obj.put("ajout du message", "ok ");
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return obj;
		
	}
	public static JSONObject listMessages(String login)
	{
		
		JSONObject obj = null;
		if(login == null)
		{
			obj = Tools.ServiceTools.refused(3);
			
		}
		else if(!Tools.UserTools.existsUser(login))
		{
			obj = Tools.ServiceTools.refused(4);
		}
		else
		{
			// renvoie la liste des messages
			
			// hisLogin dans les amis de myLogin
			
				int id = ServiceTools.getID(login);
				obj =  new JSONObject();
				try {
					obj.append("les messages", MessageTools.listMessages(id));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
						
				
				//obj.put("messages ok", "ok ");
				
			
		}
		
		return obj;
		
	}
	public static JSONObject deleteMessage(String myLogin,String key, String MessageId)
	{
		
		JSONObject obj = null;
		if(myLogin == null || key == null || MessageId == null)
		{
			obj = Tools.ServiceTools.refused(3);
			
		}
		else if(!Tools.UserTools.existsUser(myLogin))
		{
			obj = Tools.ServiceTools.refused(4);
		}
		else if(!Tools.UserTools.keyLogin(myLogin, key))
		{
			obj = Tools.ServiceTools.refused(7);
		}
		/*else if(!Tools.MessageTools.existsMessage(MessageId))
		{
			obj = Tools.ServiceTools.refused(8);
		}*/
		else
		{
			// supprimer dans la base de données le message 
			
			MessageTools.deleteMessage(MessageId);
			try {
				obj = new JSONObject();
				obj.put("suppression du message", "ok ");
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return obj;
		
	}
	public static JSONObject listMixMessages(String login) {
		// TODO Auto-generated method stub
		JSONObject obj = null;
		if(login == null)
		{
			obj = Tools.ServiceTools.refused(3);
			
		}
		else if(!Tools.UserTools.existsUser(login))
		{
			obj = Tools.ServiceTools.refused(4);
		}
		else
		{
			// renvoie la liste des messages
			
			// hisLogin dans les amis de myLogin
			
				List<String> listeFriends = Tools.FriendTools.listeFollows(login);
				listeFriends.add(login);
				obj =new JSONObject();
				try {
					obj.append("les messages",MessageTools.listMixMessages(listeFriends));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//obj.put("messages ok", "ok ");
				
			
		}
		return obj;
	}
}
