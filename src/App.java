package app;

import app.Canvas;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class App {
	public static void main(String[] args){
		JFrame frame = new JFrame("3D Function");
		JScrollBar scroll_x = new JScrollBar(JScrollBar.HORIZONTAL);
		JScrollBar scroll_y = new JScrollBar(JScrollBar.VERTICAL);
		JScrollBar scroll_distance = new JScrollBar(JScrollBar.VERTICAL);

		JTextField textfield = new JTextField(20);

		JPanel enterbox = new JPanel();
		JButton entertextButton = new JButton("Enter function");

		Canvas canvas = new Canvas();
		frame.setLayout(new BorderLayout());
		enterbox.setLayout(new BorderLayout());

		enterbox.add(new JLabel(" f(x, y) = "), BorderLayout.WEST);
		enterbox.add(textfield, BorderLayout.CENTER);
		enterbox.add(entertextButton, BorderLayout.EAST);
		enterbox.setVisible(true);
		enterbox.setSize(800, 800);

		frame.add(canvas, BorderLayout.CENTER);
		frame.add(scroll_y, BorderLayout.WEST);
		frame.add(scroll_distance, BorderLayout.EAST);
		frame.add(scroll_x, BorderLayout.NORTH);
		frame.add(enterbox, BorderLayout.SOUTH);
		frame.setSize(700, 700);
		frame.setVisible(true);

		entertextButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				canvas.setExp(textfield.getText());
				canvas.repaint();
			}
		});

		scroll_x.addAdjustmentListener(new AdjustmentListener(){  
			public void adjustmentValueChanged(AdjustmentEvent e){  
				canvas.setAngleX((double) scroll_x.getValue() / 90 * 2 * 3.141592);
				canvas.repaint();
    		}  
		});

		scroll_distance.addAdjustmentListener(new AdjustmentListener(){  
			public void adjustmentValueChanged(AdjustmentEvent e){  
				canvas.setDistanceFromOrigin((double) scroll_distance.getValue());
				canvas.repaint();
    		}  
		});

		scroll_y.addAdjustmentListener(new AdjustmentListener(){  
			public void adjustmentValueChanged(AdjustmentEvent e){  
				canvas.setAngleZ((double) scroll_y.getValue() / 90 * 2 * 3.141592);
				canvas.repaint();
    		}  
		});
	}
}
