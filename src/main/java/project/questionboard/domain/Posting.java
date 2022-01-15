package project.questionboard.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Getter
public class Posting {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

//	@ManyToOne(targetEntity = Board.class, fetch = FetchType.LAZY)
//	@JoinColumn(name = "board_id")
//	private Board board;

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

	@Column
	private LocalDateTime createDate;

	public Posting(String title, String content, String code, String writer, String deletePassword) {
		this.title = title;
		this.content = content;
		this.code = code;
		this.writer = writer;
		this.deletePassword = deletePassword;
		this.createDate = LocalDateTime.now();
	}

	public void updatePosting(String title, String content, String code, String writer, String deletePassword) {
		this.title = title;
		this.content = content;
		this.code = code;
		this.writer = writer;
		this.deletePassword = deletePassword;
		this.createDate = LocalDateTime.now();
	}

	public void setId(int id) {
		this.id = id;
	}

	public Posting() {

	}

	public boolean isWriter(String password) {
		return deletePassword.equals(password);
	}

	@Override
	public String toString() {
		return "Posting{" +
				"id=" + id +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", code='" + code + '\'' +
				", writer='" + writer + '\'' +
				", deletePassword='" + deletePassword + '\'' +
				'}';
	}
}
