package project.questionboard.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
public class Posting {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;

	@Column
	private String title;

	@Column
	private String content;

	@Column
	private String code;

	@Column
	private String writer;

	@Column(name = "delete_pw")
	private String deletePassword;

	@Column(name = "create_dt")
	private LocalDateTime createDate;

	public Posting(String title, String content, String code, String writer, String deletePassword) {
		this.title = title;
		this.content = content;
		this.code = code;
		this.writer = writer;
		this.deletePassword = deletePassword;
		this.createDate = LocalDateTime.now();
	}

	public Posting() {

	}
}
