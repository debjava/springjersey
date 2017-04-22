package com.ddlab.web.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.ddlab.util.FileUtil;

@Path("download")
public class ITCDownloadService extends BaseResource {

	/*
	 * You can directly hit the url in the browser
	 * GET localhost:8090/springjersey/api/download/image
	 */
	@Path("/image")
	@GET
	@Produces("image/jpg")
	public Response getFile() {
		byte[] docStream = FileUtil.readContents("D:/temp/k.jpg");
		return Response.ok(docStream, MediaType.APPLICATION_OCTET_STREAM)
				.header("content-disposition", "attachment; filename = k.jpg").build();
	}
	
	/*
	 * You can directly hit the url in the browser
	 * GET http://localhost:8090/springjersey/api/download/image1?fileName=k.jpg
	 */
	@Path("/image1")
	@GET
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response getFile(@QueryParam("fileName") String fileName) {
		// Do not provide path param
		Response response = null;
		String dirPath = "D:/temp";
		String filePath = dirPath + File.separator + fileName;
		try {
			if (!new File(filePath).exists())
				throw new FileNotFoundException();
			byte[] docStream = FileUtil.readContents(dirPath + File.separator
					+ fileName);
			String attachment = "attachment; filename = " + fileName;
			response = Response
					.ok(docStream, MediaType.APPLICATION_OCTET_STREAM)
					.header("content-disposition", attachment).build();

		} catch (FileNotFoundException e) {
			response = Response.serverError().status(Status.BAD_REQUEST)
					.build();
		}
		return response;
	}
	
	/* You can directly hit the url in the browser
	 * GET http://localhost:8090/springjersey/api/download/showImage?fileName=k.jpg
	 */
	@Path("/showImage")
	@GET
	@Produces("image/jpg")
	public Response showImage(@QueryParam("fileName") String fileName) {

		Response response = null;
		String dirPath = "D:/temp";
		InputStream inStream = null;
		String filePath = dirPath + File.separator + fileName;
		try {
			if (!new File(filePath).exists())
				throw new FileNotFoundException();
			inStream = new FileInputStream(filePath);
			response = Response.ok().entity(inStream).build();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			response = Response.serverError().status(Status.BAD_REQUEST)
					.build();
		}
		return response;
	}

}
