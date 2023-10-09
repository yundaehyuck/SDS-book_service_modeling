package com.c201.aebook.utils;

import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.c201.aebook.api.common.BaseResponse;
import com.c201.aebook.api.common.constants.ApplicationConstants;

import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class S3Downloader {
	private final AmazonS3Client amazonS3Client;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	public ResponseEntity<?> download(String fileUrl) throws IOException, java.io.IOException {
		S3Object s3Object = amazonS3Client.getObject(new GetObjectRequest(bucket, fileUrl));
		S3ObjectInputStream objectInputStream = s3Object.getObjectContent();
		byte[] bytes = IOUtils.toByteArray(objectInputStream);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(contentType(fileUrl));
		httpHeaders.setContentLength(bytes.length);

		String[] arr = fileUrl.split("/");
		String type = arr[arr.length - 1];
		String fileName = URLEncoder.encode(type, "UTF-8").replaceAll("\\+", "%20");
		httpHeaders.setContentDispositionFormData("attachment", fileName);

		return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
	}

	private MediaType contentType(String keyname) {
		String[] arr = keyname.split("\\.");
		String type = arr[arr.length - 1];
		switch (type) {
			case "txt":
				return MediaType.TEXT_PLAIN;
			case "png":
				return MediaType.IMAGE_PNG;
			case "jpg":
				return MediaType.IMAGE_JPEG;
			default:
				return MediaType.APPLICATION_OCTET_STREAM;
		}
	}

	public BaseResponse<?> delete(String filePath) {
		try {
			amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, filePath));
		} catch (AmazonServiceException e) {
			return new BaseResponse<>("AmazonServiceException", HttpStatus.INTERNAL_SERVER_ERROR.value(),
				ApplicationConstants.FAIL);
		} catch (SdkClientException e) {
			return new BaseResponse<>("SdkClientException", HttpStatus.INTERNAL_SERVER_ERROR.value(),
				ApplicationConstants.FAIL);
		}
		return new BaseResponse<>("SUCCESS", HttpStatus.OK.value(), ApplicationConstants.SUCCESS);
	}

}
