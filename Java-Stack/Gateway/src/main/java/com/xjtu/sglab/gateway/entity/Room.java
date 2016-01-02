package com.xjtu.sglab.gateway.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Room entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "room", catalog = "smarthome")
public class Room implements java.io.Serializable {

	// Fields

	private Integer roomId;
	private Integer userId;
	private Float roomSize;
	private Set<CircuitLine> circuitLines = new HashSet<CircuitLine>(0);
	private Set<Box> boxes = new HashSet<Box>(0);

	// Constructors

	/** default constructor */
	public Room() {
	}

	/** minimal constructor */
	public Room(Float roomSize) {
		this.roomSize = roomSize;
	}

	/** full constructor */
	public Room(Integer userId, Float roomSize, Set<CircuitLine> circuitLines,
			Set<Box> boxes) {
		this.userId = userId;
		this.roomSize = roomSize;
		this.circuitLines = circuitLines;
		this.boxes = boxes;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "room_id", unique = true, nullable = false)
	public Integer getRoomId() {
		return this.roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	@Column(name = "user_id")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "room_size", nullable = false, precision = 12, scale = 0)
	public Float getRoomSize() {
		return this.roomSize;
	}

	public void setRoomSize(Float roomSize) {
		this.roomSize = roomSize;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "room")
	public Set<CircuitLine> getCircuitLines() {
		return this.circuitLines;
	}

	public void setCircuitLines(Set<CircuitLine> circuitLines) {
		this.circuitLines = circuitLines;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "room")
	public Set<Box> getBoxes() {
		return this.boxes;
	}

	public void setBoxes(Set<Box> boxes) {
		this.boxes = boxes;
	}

}