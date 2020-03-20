package com.aps.app.complemtos;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;

public class abrirImagem {
	
	public static BufferedImage abrirImageml(Object source, File arquivo, ImageIcon imgIcon, BufferedImage bi) {

if (source instanceof File) {
			
			imgIcon = new ImageIcon (arquivo.getAbsolutePath());
			bi = new BufferedImage(imgIcon.getIconWidth(), imgIcon.getIconHeight(),
					BufferedImage.TYPE_3BYTE_BGR);
			Graphics g = bi.createGraphics();
			imgIcon.paintIcon(null, g, 0,0);
			g.dispose();			
		}
		return bi;
	}
}
