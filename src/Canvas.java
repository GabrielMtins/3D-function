package app;

import app.Vec3;
import app.Parser;

import javax.swing.JOptionPane;
import javax.swing.*;
import java.util.*;
import java.awt.*;

// a canvas to draw the function 
public class Canvas extends JPanel{
	private String exp;
	private int internal_width, internal_height;
	private double angle_x, angle_z;
	private double delta; /* basically a tile size */
	private int bound; /* the boundarie of the map */
	private double distance_from_origin;
	private Parser parser;

	public Canvas(){
		parser = new Parser();
		bound = 5;

		internal_width = internal_height = 400;
		distance_from_origin = 20;
		angle_x = 0;
		angle_z = 0;

		exp = "cos((x+y)*1.3)";

		delta = 0.5;
	}

	/* a wrapper to use the parser module easier */
	private double f(double x, double y){
		parser.setVariable("x", x);
		parser.setVariable("y", y);

		return parser.solveExp(exp);
	}

	public void setExp(String exp){
		this.exp = exp;
	}

	public void setAngleX(double angle_x){
		this.angle_x = angle_x;
	}

	public void setAngleZ(double angle_z){
		this.angle_z = angle_z;
	}

	public void setDistanceFromOrigin(double distance){
		distance_from_origin = distance;
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.white);

		g.fillRect(0, 0, getWidth(), getHeight());

		Graphics2D g2d = (Graphics2D) g;

		/* set the internal width proportionally */
		internal_width = getWidth() * internal_height / getHeight();
		g2d.scale((double) getWidth() / internal_width, (double) getHeight() / internal_height);

		drawWireframe(g2d);
	}

	private void drawWireframe(Graphics g2d){
		Vec3 camera = new Vec3(0, -distance_from_origin, 0);

		g2d.setColor(Color.black);

		for(double i = -bound; i < bound; i += delta){
			for(double j = -bound; j < bound; j += delta){
				Vec3 p1 = new Vec3(i, j, f(i, j));
				Vec3 p2 = new Vec3(i + delta, j, f(i + delta, j));
				Vec3 p3 = new Vec3(i, j + delta, f(i, j + delta));
				Vec3 p4 = new Vec3(i + delta, j + delta, f(i + delta, j + delta));

				try{
					p1 = projection(p1, camera);
					p2 = projection(p2, camera);
					p3 = projection(p3, camera);
					p4 = projection(p4, camera);

					g2d.drawLine((int) p1.x, (int) p1.y, (int) p2.x, (int) p2.y);
					g2d.drawLine((int) p1.x, (int) p1.y, (int) p3.x, (int) p3.y);
					g2d.drawLine((int) p4.x, (int) p4.y, (int) p2.x, (int) p2.y);
					g2d.drawLine((int) p4.x, (int) p4.y, (int) p3.x, (int) p3.y);
				}
				catch(Exception ex){
				}
			}
		}
	}

	private Vec3 projection(Vec3 a, Vec3 camera) throws Exception{
		/* project point to camera */
		a = a.rotateX(angle_x);
		a = a.rotateZ(angle_z);
		a = Vec3.sub(a, camera);
		if(a.y <= 0.5) throw new Exception("Point is behind camera");

		a.x = a.x / a.y;
		a.z = a.z / a.y;

		a.y = a.z;
		a.y = -a.y;
		a.y += 0.5;

		a.x *= internal_height;
		a.x += internal_width / 2;
		a.y *= internal_height;

		return a;
	}
}
