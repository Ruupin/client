package rina.util;

// Java.
import java.awt.*;

/**
 * @author Rina!
 *
 * Created by Rina in 27/07/2020.
 *
 **/
public class TurokRect {
	public int x;
	public int y;

	public int width;
	public int height;

	public String tag;

	public TurokRect(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;

		this.width  = width;
		this.height = height;

		this.tag = "nontag";
	}

	public TurokRect(String tag, int x, int y, int width, int height) {
		this.x = x;
		this.y = y;

		this.width  = width;
		this.height = height;

		this.tag = "nontag";
	}

	public TurokRect(int x, int y) {
		this.x = x;
		this.y = y;

		this.width  = 0;
		this.height = 0;

		this.tag = "nontag";		
	}

	public TurokRect(String tag, int x, int y) {
		this.x = x;
		this.y = y;

		this.width  = 0;
		this.height = 0;

		this.tag = tag;		
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getTag() {
		return this.tag;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public boolean collide(int x, int y) {
		if (x >= getX() && x <= getX() + getWidth() && y >= getY() && y <= getY() + getHeight()) {
			return true;
		}

		return false;
	}
}