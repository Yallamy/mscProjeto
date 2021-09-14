package br.com.mesttra.msc.projeto.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import br.com.mesttra.msc.projeto.enums.DestinationTypeEnum;
import br.com.mesttra.msc.projeto.useful.Message;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Classe que representa a entidade Secretariat.
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 14 de set de 2021
 */
@Table(name = "Secretariat")
@Entity
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(of = { "id" })
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_project")
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "secretariatId", nullable = false)
	@NotNull(message = Message.SECRETARIAT_ID_REQUIRED)
	private Long secretariatId;
	
	@Column(name = "cost", nullable = false)
	@NotNull(message = Message.COST_REQUIRED)
	private BigDecimal cost;
	
	@Column(name = "title", nullable = false)
	@NotNull(message = Message.TITLE_REQUIRED)
	private String title;
	
	@Column(name = "description", nullable = false, length = 30)
	@NotNull(message = Message.DESCRIPTION_REQUIRED)
	private String description;
	
	@Column(name = "folder", nullable = false, unique = true)
	@NotNull(message = Message.FOLDER_REQUIRED)
	@Enumerated(EnumType.STRING)
	private DestinationTypeEnum folder;

}
