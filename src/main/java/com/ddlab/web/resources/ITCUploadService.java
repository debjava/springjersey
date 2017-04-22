package com.ddlab.web.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.BodyPartEntity;
import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.ddlab.entity.User;
import com.ddlab.util.Constants;
import com.ddlab.util.FileUtil;

@Path("upload")
public class ITCUploadService extends BaseResource {

	//POST http://localhost:8090/springjersey/api/upload/image
	//In case of postman client
	//Choose form-data, file : c.jpg
	@POST
	@Path("/image")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(FormDataMultiPart form) {
		FormDataBodyPart filePart = form.getField("file");
		ContentDisposition headerOfFilePart = filePart.getContentDisposition();
		InputStream fileInputStream = filePart.getValueAs(InputStream.class);
		String filePath = Constants.SERVER_UPLOAD_LOCATION_FOLDER + headerOfFilePart.getFileName();
		// save the file to the server
		FileUtil.saveFile(fileInputStream, filePath);
		String output = "File saved to server location using FormDataMultiPart : " + filePath;
		try {
			fileInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(output).build();
	}
	
	/*
	 * Another way to upload file
	 * POST http://localhost:8090/springjersey/api/upload/image1
	 * In case of postman client, choose form-data file : a.jpg
	 * 
	 */
	@POST
	@Path("/image1")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(
			@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition contentDispositionHeader) {
		String filePath = Constants.SERVER_UPLOAD_LOCATION_FOLDER
				+ contentDispositionHeader.getFileName();
		// save the file to the server
		String output = "";
		try {
			FileUtil.saveFile(fileInputStream, filePath);
			output = "File saved to server location : " + filePath
					+ " successfully";
		} catch (Exception e) {
			e.printStackTrace();
			output = "Unexpected server exception while uploading the file.";
		}
		return Response.status(200).entity(output).build();
	}
	
	/*
	 * Posting image with data
	 * POST http://localhost:8090/springjersey/api/upload/imageWithData
	 * In case of postman client choose form-data
	 * file: a.jpg
	 * user: {  "firstName" : "Deb", "lastName" : "Mishra","id" : 1 }
	 */
	@POST
	@Path("/imageWithData")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(FormDataMultiPart form, @FormDataParam("user") String user) {

		System.out.println("User-------->" + user.toString());
		FormDataBodyPart filePart = form.getField("file");
		ContentDisposition headerOfFilePart = filePart.getContentDisposition();
		InputStream fileInputStream = filePart.getValueAs(InputStream.class);
		String filePath = Constants.SERVER_UPLOAD_LOCATION_FOLDER
				+ headerOfFilePart.getFileName();
		// save the file to the server
		FileUtil.saveFile(fileInputStream, filePath);
		String output = "File saved to server location using FormDataMultiPart : "
				+ filePath;
		return Response.status(200).entity(output).build();
	}
	
	/*
	 * Posting image with data
	 * POST http://localhost:8090/springjersey/api/upload/imageWithData1
	 * In case of postman client choose form-data
	 * file: a.jpg
	 * user: {  "firstName" : "Deb", "lastName" : "Mishra","id" : 1 }
	 */
	@POST
	@Path("/imageWithData1")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFileWithData(FormDataMultiPart form) {
		
		Map<String, List<FormDataBodyPart>> params = form.getFields();
		System.out.println(params);

		String myJson = params.get("user").get(0).getValue();
		System.out.println("myjson---------->" + myJson);
		User user = User.toUser(myJson);
		System.out.println("User-------->" + user.toXML());

		FormDataBodyPart filePart = form.getField("file");
		ContentDisposition headerOfFilePart = filePart.getContentDisposition();
		InputStream fileInputStream = filePart.getValueAs(InputStream.class);
		String filePath = Constants.SERVER_UPLOAD_LOCATION_FOLDER
				+ headerOfFilePart.getFileName();
		// save the file to the server
		FileUtil.saveFile(fileInputStream, filePath);
		String output = "File saved to server location using FormDataMultiPart : "
				+ filePath;
		try {
			fileInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(output).build();
	}
	
	/*
	 * Posting multiple images
	 * POST http://localhost:8090/springjersey/api/upload/multiupload
	 * In case of postman client choose form-data
	 * file1: a.jpg
	 * file2: b.jpg
	 * You can also use the html file called form.html
	 */
	@POST
	@Path("/multiupload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadMultipleFile(FormDataMultiPart form) {

		BodyPartEntity bodyPartEntity;
		for (BodyPart part : form.getBodyParts()) {
			bodyPartEntity = (BodyPartEntity) part.getEntity();
			String fileName = part.getContentDisposition().getFileName();
			System.out
			.println("part.getContentDisposition().getFileName()---------->"
					+ fileName);
			InputStream fileInputStream = bodyPartEntity.getInputStream();
			String filePath = Constants.SERVER_UPLOAD_LOCATION_FOLDER + fileName;
			FileUtil.saveFile(fileInputStream, filePath);
		}
		return Response.status(200)
				.entity("All Files uploaded successfully ... ").build();
	}

}
