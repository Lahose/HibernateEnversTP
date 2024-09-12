package com.tpa.persistence.audit;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import com.tpa.persistence.config.CustomListerner;

import lombok.Data;

@Data
@Entity
@Table(name="REVISION_INFO")
@RevisionEntity(CustomListerner.class)
public class Revision implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "revision_seq")
	@SequenceGenerator(name = "revision_seq", sequenceName = "revision_sequence")
	@RevisionNumber
	private int id;

	@Column(name="REVISION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	@RevisionTimestamp
	private Date date;
	
	

}
