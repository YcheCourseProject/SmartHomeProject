package com.example.smarthomeapp.model;

import java.util.HashSet;
import java.util.Set;

/**
 */
public class Room implements java.io.Serializable {

	// Fields

	private Integer roomId;
	private Float roomSize;
	private Set<CircuitLine> circuitLines = new HashSet<CircuitLine>(0);
	private Set<Box> boxes = new HashSet<Box>(0);

	// Constructors

	/** default constructor */
	public Room() {
	}

	/** full constructor */
	public Room(Float roomSize, Set<CircuitLine> circuitLines, Set<Box> boxes) {
		this.roomSize = roomSize;
		this.circuitLines = circuitLines;
		this.boxes = boxes;
	}

	// Property accessors
	public Integer getRoomId() {
		return this.roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public Float getRoomSize() {
		return this.roomSize;
	}

	public void setRoomSize(Float roomSize) {
		this.roomSize = roomSize;
	}

	public Set<CircuitLine> getCircuitLines() {
		return this.circuitLines;
	}

	public void setCircuitLines(Set<CircuitLine> circuitLines) {
		this.circuitLines = circuitLines;
	}

	public Set<Box> getBoxes() {
		return this.boxes;
	}

	public void setBoxes(Set<Box> boxes) {
		this.boxes = boxes;
	}

}
