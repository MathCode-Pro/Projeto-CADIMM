package com.aps.app.complemtos;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ManipularImagem {

	public static BufferedImage setImagemDimensao2(byte[] caminhoImg, Integer imgLargura, Integer imgAltura) {
		Double novaImgLargura = null;
		Double novaImgAltura = null;
		Double imgProporcao = null;
		Graphics2D g2d = null;
		ImageIcon imagem = null;
		BufferedImage novaImagem = null;

		try {
			imagem = (new ImageIcon(caminhoImg));

			novaImgLargura = (double) imagem.getIconWidth();

			novaImgAltura = (double) imagem.getIconHeight();

			if (novaImgLargura >= imgLargura) {
				imgProporcao = (novaImgAltura / novaImgLargura);
				novaImgLargura = (double) imgLargura;

				novaImgAltura = (novaImgLargura * imgProporcao);

				while (novaImgAltura > imgAltura) {
					novaImgLargura = (double) (--imgLargura);
					novaImgAltura = (novaImgLargura * imgProporcao);
				}
			} else if (novaImgAltura >= imgAltura) {
				imgProporcao = (novaImgLargura / novaImgAltura);
				novaImgAltura = (double) imgAltura;
				while (novaImgLargura > imgLargura) {
					novaImgAltura = (double) (--imgAltura);
					novaImgLargura = (novaImgAltura * imgProporcao);
				}
			}

			novaImagem = new BufferedImage(novaImgLargura.intValue(), novaImgAltura.intValue(),
					BufferedImage.TYPE_3BYTE_BGR);
			g2d = novaImagem.createGraphics();
			imagem.paintIcon(null, g2d, 0, 0);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"A digital informada já está cadastrada! Informe uma digital não registrada!", "Erro!",
					JOptionPane.ERROR_MESSAGE);
		}

		return novaImagem;
	}
	
	public static BufferedImage setImagemDimensao(String caminhoImg, Integer imgLargura, Integer imgAltura) {
		Double novaImgLargura = null;
		Double novaImgAltura = null;
		Double imgProporcao = null;
		Graphics2D g2d = null;
		BufferedImage imagem = null, novaImagem = null;

		try {
			imagem = ImageIO.read(new File(caminhoImg));
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao recuperar a imagem!", "Erro!", JOptionPane.ERROR_MESSAGE);

		}

		novaImgLargura = (double) imagem.getWidth();

		novaImgAltura = (double) imagem.getHeight();

		if (novaImgLargura >= imgLargura) {
			imgProporcao = (novaImgAltura / novaImgLargura);
			novaImgLargura = (double) imgLargura;

			novaImgAltura = (novaImgLargura * imgProporcao);

			while (novaImgAltura > imgAltura) {
				novaImgLargura = (double) (--imgLargura);
				novaImgAltura = (novaImgLargura * imgProporcao);
			}
		} else if (novaImgAltura >= imgAltura) {
			imgProporcao = (novaImgLargura / novaImgAltura);
			novaImgAltura = (double) imgAltura;
			while (novaImgLargura > imgLargura) {
				novaImgAltura = (double) (--imgAltura);
				novaImgLargura = (novaImgAltura * imgProporcao);
			}
		}

		novaImagem = new BufferedImage(novaImgLargura.intValue(), novaImgAltura.intValue(), BufferedImage.TYPE_3BYTE_BGR);
		g2d = novaImagem.createGraphics();
		g2d.drawImage(imagem, 0, 0, novaImgLargura.intValue(), novaImgAltura.intValue(), null);

		return novaImagem;
	}

	public static byte[] getImgBytes(BufferedImage image) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, "PNG", baos);
		} catch (IOException ex) {
		}

		return baos.toByteArray();
	}

	public static javax.swing.JLabel exibiImagemLabel(BufferedImage img, javax.swing.JLabel label){

		if (img != null) {

			ImageIcon icon = new ImageIcon(img.getScaledInstance(label.getWidth(), label.getHeight(), 100));
			label.setIcon(icon);
			label.repaint();

		} else {
			label.setIcon(null);
		}
		return label;

	}
}
