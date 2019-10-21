package com.sap.spring.xsuaa.cf.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Getter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Movie {
	@Id
	public ObjectId _id;
	public String name;
	public String description;
	public String date;
	public int seats;

	// Returns ObjectId in HexString format
	public String get_id() {
		return this._id.toHexString();
	}

}
