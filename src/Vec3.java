package app;

import java.util.*;

public class Vec3{
	public double x, y, z;

	public Vec3(double ex_x, double ex_y, double ex_z){
		x = ex_x;
		y = ex_y;
		z = ex_z;
	}

	public Vec3(){
		x = y = z = 0;
	}

	public static Vec3 add(Vec3 a, Vec3 b){
		return new Vec3(a.x + b.x, a.y + b.y, a.z + b.z);
	}

	public static Vec3 sub(Vec3 a, Vec3 b){
		return new Vec3(a.x - b.x, a.y - b.y, a.z - b.z);
	}

	public static double dotProduct(Vec3 a, Vec3 b){
		return a.x * b.x + a.y * b.y + a.z * b.z;
	}

	public static Vec3 crossProduct(Vec3 a, Vec3 b){
		return new Vec3(
				a.y * b.z - a.z * b.y,
				a.z * b.x - a.x * b.z,
				a.x * b.y - a.y * b.x
		);
	}

	public double size(){
		return Math.sqrt(x * x + y * y + z * z);
	}

	public void normalize(){
		double s = size();
		x /= s;
		y /= s;
		z /= s;
	}

	public Vec3 mul(double k){
		return new Vec3(x * k, y * k, z * k);
	}

	public Vec3 div(double k){
		return new Vec3(x / k, y / k, z / k);
	}

	public Vec3 rotateX(double angle){
		return new Vec3(
					Math.cos(angle) * x - Math.sin(angle) * y,
					Math.sin(angle) * x + Math.cos(angle) * y,
					z
				);
	}

	public Vec3 rotateZ(double angle){
		return new Vec3(
					x,
					Math.cos(angle) * y - Math.sin(angle) * z,
					Math.sin(angle) * y + Math.cos(angle) * z
				);
	}
}
