package com.aps.app.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.aps.app.complemtos.CriptMD5;
import com.aps.app.validDig.controleNumero;
import com.aps.model.bean.ConexaoBEAN;
import com.aps.model.dao.ConexaoDAO;

public class login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JButton btnLogin;
	private static JPasswordField tfSenha;
	private static JFormattedTextField tfNome;
	private static ConexaoBEAN bean = new ConexaoBEAN();
	private static ConexaoDAO dao = new ConexaoDAO();
	private static String o = null;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
					tfSenha.requestFocus();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public login() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(login.class.getResource("/com/aps/app/ícone/CADIMM.png")));

		setTitle("Login");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 422, 336);
		contentPane = new JPanel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Image image = getImage("/com/aps/app/fundo/fundo3.jpg");

			public Image getImage(String path) {
				URL imageURL = getClass().getResource(path);
				if (imageURL == null)
					return null;
				return new ImageIcon(imageURL).getImage();
			}

			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				Dimension dDesktop = this.getSize();
				double width = dDesktop.getWidth();
				double height = dDesktop.getHeight();
				Image background = new ImageIcon(this.image.getScaledInstance((int) width, (int) height, 1)).getImage();
				g.drawImage(background, 0, 0, this);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);
		setResizable(false);

		JLabel lblUsurio = new JLabel("Usu\u00E1rio: ");
		lblUsurio.setForeground(Color.WHITE);
		lblUsurio.setBounds(5, 28, 50, 14);

		tfNome = new JFormattedTextField();
		tfNome.setEditable(false);
		tfNome.setBounds(65, 25, 341, 20);
		tfNome.setHorizontalAlignment(SwingConstants.LEFT);

		JLabel lblSenha = new JLabel("Senha: ");
		lblSenha.setForeground(Color.WHITE);
		lblSenha.setBounds(7, 86, 48, 14);

		tfSenha = new JPasswordField();
		tfSenha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnLogin.doClick();
					e.consume();
					return;
				}
			}
		});
		tfSenha.setBounds(65, 83, 119, 20);
		tfSenha.setHorizontalAlignment(SwingConstants.LEFT);
		tfSenha.setDocument(new controleNumero(11));

		btnLogin = new JButton("Logar-se");
		btnLogin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnLogin.doClick();
					e.consume();
					return;
				}
			}
		});
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(Color.WHITE);
		btnLogin.setBounds(5, 136, 99, 23);
		btnLogin.setOpaque(false);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_btnLogin_actionPerformed1(arg0);
			}
		});

		contentPane.setLayout(null);
		contentPane.add(lblUsurio);
		contentPane.add(lblSenha);
		contentPane.add(tfSenha);
		contentPane.add(tfNome);
		contentPane.add(btnLogin);

	}

	private void do_btnLogin_actionPerformed1(ActionEvent arg0) {

		o = null;

		limparBean();

		o = String.valueOf(tfSenha.getPassword());

		if (o.isEmpty()) {

			JOptionPane.showMessageDialog(null, "Preêncha o campo de senha corretamente!", "Atenção!",
					JOptionPane.WARNING_MESSAGE);
			apagar();
			return;
		} else {

			dao.conectar();
			
			if (dao.conectou == true) {

				bean.setSenhaHash(CriptMD5.md5(o));

				if (dao.consultarADM(bean) == true) {

					tfNome.setText(ConexaoBEAN.getNome());
					dao.fecharCon();
					JOptionPane.showMessageDialog(null, "Bem vindo!", "Olá!", JOptionPane.DEFAULT_OPTION);
					windowClosing();

				} else if (dao.consultarADM(bean) == false) {
					JOptionPane.showMessageDialog(null, "Usuário não encontrado! Insira um usuário já cadastrado. "
							+ "Caso não seja um usuário cadastrado, solicite ao Administrador para registra-lo.",
							"Erro!", JOptionPane.ERROR_MESSAGE);
					limparBean();
					apagar();
					return;
				}

			} else {
				JOptionPane.showMessageDialog(null, "Nao foi possivel conectar ao Banco de Dados.", "Erro!",
						JOptionPane.ERROR_MESSAGE);
				apagar();
				return;
			}
		}
	}

	private void windowClosing() {

		apagar();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaControle frame = new TelaControle();					
					frame.setVisible(true);
					frame.dadosLogin();
					dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static void apagar() {
		tfNome.setText(null);
		tfSenha.setText(null);
	}

	private void limparBean() {
		bean.setNome(null);
		bean.setSenhaHash(null);
	}
}
