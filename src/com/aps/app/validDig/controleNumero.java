package com.aps.app.validDig;

import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class controleNumero extends PlainDocument {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int quantidadeMax;

	public controleNumero(int maxLen) {
		super();
		quantidadeMax = maxLen;
	}

	@Override
	public void insertString(int offset, String str, javax.swing.text.AttributeSet attr) throws BadLocationException {

		if (str == null || getLength() == quantidadeMax)
			return;

		int totalquantia = (getLength() + str.length());

		if (totalquantia <= quantidadeMax) {
			super.insertString(offset, str.replaceAll("[^0-9]", ""), attr);
			return;
		}

		String nova = str.substring(0, getLength() - quantidadeMax);
		super.insertString(offset, nova, attr);
	}

}
