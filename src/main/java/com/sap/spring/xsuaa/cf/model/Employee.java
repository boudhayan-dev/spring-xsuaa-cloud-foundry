package com.sap.spring.xsuaa.cf.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Employee {
	
	@Id
	
	public ObjectId _id;
	public String firstName;
	public String lastName;
	public long contact;
	public String email;
	
	// Returns ObjectId in HexString format
	public String get_id() {
		return this._id.toHexString();
	}
}
