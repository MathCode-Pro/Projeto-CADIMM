package com.aps.app.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.MaskFormatter;

import com.aps.app.complemtos.ManipularImagem;
import com.aps.app.complemtos.abrirImagem;
import com.aps.app.validDig.controleLetra;
import com.aps.app.validDig.controleLetra_Numero;
import com.aps.app.validDig.controleNumero;
import com.aps.model.bean.ConexaoBEAN;
import com.aps.model.dao.ConexaoDAO;

import net.miginfocom.swing.MigLayout;
import net.sf.jasperreports.engine.JRException;

public class TelaControle extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane, painelImagemAlt, panel_2, panel_1, panelDados, panelFoto, panelFotoAlt, panelDados2,
			panelDadosAlt2, panelDadosAlt, panelExcluir, panelDadosExcl, panelFotoExcl, painelImagemExlc;
	static ConexaoBEAN bean = new ConexaoBEAN();
	static ConexaoDAO dao = new ConexaoDAO();
	private static JFormattedTextField tfNome, tfCPF;
	private File imagem;
	private static JLabel lblImagemAlt;
	private static JButton btnRegistrar, btnAlterar;
	private JButton btnBuscarFot, btnDeslogar, btnBuscarFotAlt, btnExcluir, btnLimparTudo, btnBuscarExcl, btnBuscarAlt;
	private BufferedImage image;
	private JFileChooser fileChooser;
	private JToggleButton tglbtnDesativarDicas;
	private String dica1 = "Obs: para ativar o botão \"Registrar\" busque a foto 3x4 do usuário.";
	private String dica2 = "Obs: basta digitar o CPF do membro que será descadastrado do sistema e clicar no botão \"Excluir\".";
	private String dica3 = "Obs 2: os campos marcados com \" * \" s\u00E3o obrigat\u00F3rios.";
	private String dica6 = "Obs: para ativar os botões \"Alterar\" e \"Imprimir\" busque o membro registrado pelo seu CPF ou nome registrado e mude os dados que deseja alterar.";
	private String dica4 = "Obs: para ativar o botão \"Excluir\" busque o membro registrado pelo seu CPF ou nome registrado.";
	private JTextPane txtpnParaAtivarO_1, txtpnParaAtivarO_1Alt;
	private String loginNome;
	protected TelaControle frame;

	private JLabel lblObsOsCampos, lblObsOsCamposAlt, lblUsurio, lblRg, lblNascimento, lblTelefone, lblEmail,
			lblTitElei, lblZona, lblSecao, lblTtuloEleitoral, lblDadosPessoais, lblEscolaridade, lblEstCivil,
			lblConsagracao, lblFiliacao, lblConsPret, lblConsAt, lblEndereço, lblCEP, lblEstado, lblCidade, lblBairro,
			lblFamiliares, lblPai, lblnomeDaMe, lblConjugue, lblTelConj, lblCpfAlt, lblTelefoneAlt, lblObsExcl,
			lblNomeExcl, lblCPFExcl, lblImagemExcl, lblComplemento, lblCompleAlt;

	private JFormattedTextField tfRg, tfNascimento, tfTelefone, tfEmail, tfTitElei, tfZona, tfSecao, tfEscolaridade,
			tfFiliacao, tfConsPret, tfConsAt, tfCEP, tfCEPAlt, tfBairro, tfBairroAlt, tfPai, tfPaiAlt, tfConjugue,
			tfConjugueAlt, tfTelConj, tfTelConjAlt, tfMae, tfMaeAlt, tfConsPretAlt, tfFiliacaoAlt, tfConsAtAlt,
			tfSecaoAlt, tfZonaAlt, tfTitEleiAlt, tfEscolaridadeAlt, tfEmailAlt, tfTelefoneAlt, tfNascimentoAlt, tfRgAlt,
			tfCPFAlt, tfNomeAlt, tfNomeExcl, tfCPFExcl, tfComplemento, tfComplementoAlt;

	private JComboBox<Object> cbEstCivil, cbEstado, cbEstadoAlt, cbCidade, cbCidadeAlt, cbEstCivilAlt;

	private String fundo = "/com/aps/app/fundo/fundo3.jpg";
	private JTextPane txtpnObsParaAtivar;
	private ImageIcon imgIcon;
	private JPanel painelImagem;
	private JLabel lblImagem;
	private JButton btnImprimir;

	// contrutor da janela
	public TelaControle() throws ParseException {

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(TelaControle.class.getResource("/com/aps/app/ícone/CADIMM.png")));
		setTitle(
				"CADIMM Controle de Membros                                                                               "
						+ "                                                                                                       "
						+ "                      by Matheus Ol. Sant. ;)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setSize(1451, 650);

		contentPane = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Image image = getImage(fundo);

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
		contentPane.setOpaque(false);
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(Color.WHITE);
		tabbedPane.setBackground(new Color(0, 0, 0, 0));
		tabbedPane.setOpaque(false);
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 11));
		tabbedPane.setBorder(null);

		JPanel panelInserir = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Image image = getImage(fundo);

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
		panelInserir.setForeground(Color.WHITE);
		panelInserir.setOpaque(false);
		panelInserir.setToolTipText("");
		tabbedPane.addTab("Registrar", panelInserir);
		tabbedPane.setBackgroundAt(0, new Color(0, 0, 0, 0));
		panelInserir.setLayout(
				new MigLayout("", "[521.00px,left][10.00px,center][521.00px,left][85.00px,fill][341.00px,right]",
						"[315.00px,grow][558px][178.00][90.00,grow,bottom]"));

		panelDados = new JPanel();
		panelDados.setOpaque(false);
		panelInserir.add(panelDados, "cell 0 0 2 1,alignx left,aligny top");
		GridBagLayout gbl_panelDados = new GridBagLayout();
		gbl_panelDados.columnWidths = new int[] { 82, 170, 0, 16, 0 };
		gbl_panelDados.rowHeights = new int[] { 0, 20, 20, 20, 20, 0, 0, 0, 0, 20, 20, 20, 20, 0, 20, 0, 0, 0, 0 };
		gbl_panelDados.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelDados.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0 };
		panelDados.setLayout(gbl_panelDados);

		lblDadosPessoais = new JLabel("Dados pessoais:");
		lblDadosPessoais.setForeground(Color.YELLOW);
		GridBagConstraints gbc_lblDadosPessoais = new GridBagConstraints();
		gbc_lblDadosPessoais.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblDadosPessoais.gridwidth = 2;
		gbc_lblDadosPessoais.insets = new Insets(0, 0, 5, 5);
		gbc_lblDadosPessoais.gridx = 0;
		gbc_lblDadosPessoais.gridy = 0;
		panelDados.add(lblDadosPessoais, gbc_lblDadosPessoais);

		JLabel lblnome = new JLabel("*Nome: ");
		GridBagConstraints gbc_lblnome = new GridBagConstraints();
		gbc_lblnome.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblnome.insets = new Insets(0, 0, 5, 5);
		gbc_lblnome.gridx = 0;
		gbc_lblnome.gridy = 1;
		panelDados.add(lblnome, gbc_lblnome);
		lblnome.setForeground(Color.WHITE);

		tfNome = new JFormattedTextField();
		tfNome.setColumns(30);
		tfNome.setDocument(new controleLetra(80));
		GridBagConstraints gbc_tfNome = new GridBagConstraints();
		gbc_tfNome.gridwidth = 3;
		gbc_tfNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNome.insets = new Insets(0, 0, 5, 0);
		gbc_tfNome.gridx = 1;
		gbc_tfNome.gridy = 1;
		panelDados.add(tfNome, gbc_tfNome);

		JLabel lblCpf = new JLabel("*CPF: ");
		GridBagConstraints gbc_lblCpf = new GridBagConstraints();
		gbc_lblCpf.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCpf.insets = new Insets(0, 0, 5, 5);
		gbc_lblCpf.gridx = 0;
		gbc_lblCpf.gridy = 2;
		panelDados.add(lblCpf, gbc_lblCpf);
		lblCpf.setForeground(Color.WHITE);

		tfCPF = new JFormattedTextField();
		tfCPF.setColumns(15);
		tfCPF.setDocument(new controleNumero(11));
		GridBagConstraints gbc_tfCPF = new GridBagConstraints();
		gbc_tfCPF.gridwidth = 2;
		gbc_tfCPF.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfCPF.insets = new Insets(0, 0, 5, 5);
		gbc_tfCPF.gridx = 1;
		gbc_tfCPF.gridy = 2;
		panelDados.add(tfCPF, gbc_tfCPF);

		lblRg = new JLabel("*RG: ");
		lblRg.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblRg = new GridBagConstraints();
		gbc_lblRg.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRg.insets = new Insets(0, 0, 5, 5);
		gbc_lblRg.gridx = 0;
		gbc_lblRg.gridy = 3;
		panelDados.add(lblRg, gbc_lblRg);

		tfRg = new JFormattedTextField();
		tfRg.setColumns(15);
		tfRg.setDocument(new controleNumero(14));
		GridBagConstraints gbc_tfRg = new GridBagConstraints();
		gbc_tfRg.insets = new Insets(0, 0, 5, 5);
		gbc_tfRg.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfRg.gridx = 1;
		gbc_tfRg.gridy = 3;
		panelDados.add(tfRg, gbc_tfRg);

		lblNascimento = new JLabel("*Nascimento: \r\n");
		lblNascimento.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNascimento = new GridBagConstraints();
		gbc_lblNascimento.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNascimento.insets = new Insets(0, 0, 5, 5);
		gbc_lblNascimento.gridx = 0;
		gbc_lblNascimento.gridy = 4;
		panelDados.add(lblNascimento, gbc_lblNascimento);
		tfNascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
		tfNascimento.setColumns(15);
		GridBagConstraints gbc_tfNascimento = new GridBagConstraints();
		gbc_tfNascimento.insets = new Insets(0, 0, 5, 5);
		gbc_tfNascimento.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNascimento.gridx = 1;
		gbc_tfNascimento.gridy = 4;
		panelDados.add(tfNascimento, gbc_tfNascimento);

		lblTelefone = new JLabel("*Telefone: ");
		lblTelefone.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblTelefone = new GridBagConstraints();
		gbc_lblTelefone.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTelefone.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefone.gridx = 0;
		gbc_lblTelefone.gridy = 5;
		panelDados.add(lblTelefone, gbc_lblTelefone);
		tfTelefone = new JFormattedTextField(new MaskFormatter("(##)#####-####"));
		tfTelefone.setText("(  )     -    ");
		tfTelefone.setColumns(15);
		GridBagConstraints gbc_tfTelefone = new GridBagConstraints();
		gbc_tfTelefone.insets = new Insets(0, 0, 5, 5);
		gbc_tfTelefone.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfTelefone.gridx = 1;
		gbc_tfTelefone.gridy = 5;
		panelDados.add(tfTelefone, gbc_tfTelefone);

		lblEmail = new JLabel("E-mail: ");
		lblEmail.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 6;
		panelDados.add(lblEmail, gbc_lblEmail);

		tfEmail = new JFormattedTextField();
		tfEmail.setColumns(15);
		GridBagConstraints gbc_tfEmail = new GridBagConstraints();
		gbc_tfEmail.gridwidth = 3;
		gbc_tfEmail.insets = new Insets(0, 0, 5, 0);
		gbc_tfEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfEmail.gridx = 1;
		gbc_tfEmail.gridy = 6;
		panelDados.add(tfEmail, gbc_tfEmail);

		lblEscolaridade = new JLabel("*Escolaridade: ");
		lblEscolaridade.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblEscolaridade = new GridBagConstraints();
		gbc_lblEscolaridade.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEscolaridade.insets = new Insets(0, 0, 5, 5);
		gbc_lblEscolaridade.gridx = 0;
		gbc_lblEscolaridade.gridy = 7;
		panelDados.add(lblEscolaridade, gbc_lblEscolaridade);

		tfEscolaridade = new JFormattedTextField();
		tfEscolaridade.setColumns(15);
		tfEscolaridade.setDocument(new controleLetra(50));
		GridBagConstraints gbc_tfEscolaridade = new GridBagConstraints();
		gbc_tfEscolaridade.insets = new Insets(0, 0, 5, 5);
		gbc_tfEscolaridade.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfEscolaridade.gridx = 1;
		gbc_tfEscolaridade.gridy = 7;
		panelDados.add(tfEscolaridade, gbc_tfEscolaridade);

		lblEstCivil = new JLabel("Estado civil: ");
		lblEstCivil.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblEstCivil = new GridBagConstraints();
		gbc_lblEstCivil.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEstCivil.insets = new Insets(0, 0, 5, 5);
		gbc_lblEstCivil.gridx = 0;
		gbc_lblEstCivil.gridy = 8;
		panelDados.add(lblEstCivil, gbc_lblEstCivil);

		cbEstCivil = new JComboBox<Object>();
		cbEstCivil.setModel(new DefaultComboBoxModel<Object>(
				new String[] { "", "Solteiro(a)", "Casado(a)", "Separado(a)", "Divorciado(a)", "Vi\u00FAvo(a)" }));
		cbEstCivil.setMaximumRowCount(5);
		GridBagConstraints gbc_cbEstCivil = new GridBagConstraints();
		gbc_cbEstCivil.anchor = GridBagConstraints.WEST;
		gbc_cbEstCivil.insets = new Insets(0, 0, 5, 5);
		gbc_cbEstCivil.gridx = 1;
		gbc_cbEstCivil.gridy = 8;
		panelDados.add(cbEstCivil, gbc_cbEstCivil);

		lblTtuloEleitoral = new JLabel("T\u00EDtulo Eleitoral:");
		lblTtuloEleitoral.setForeground(Color.YELLOW);
		GridBagConstraints gbc_lblTtuloEleitoral = new GridBagConstraints();
		gbc_lblTtuloEleitoral.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTtuloEleitoral.gridwidth = 2;
		gbc_lblTtuloEleitoral.insets = new Insets(0, 0, 5, 5);
		gbc_lblTtuloEleitoral.gridx = 0;
		gbc_lblTtuloEleitoral.gridy = 10;
		panelDados.add(lblTtuloEleitoral, gbc_lblTtuloEleitoral);

		lblTitElei = new JLabel("*N\u00FAmero: \r\n ");
		lblTitElei.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblTitElei = new GridBagConstraints();
		gbc_lblTitElei.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTitElei.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitElei.gridx = 0;
		gbc_lblTitElei.gridy = 11;
		panelDados.add(lblTitElei, gbc_lblTitElei);

		tfTitElei = new JFormattedTextField();
		tfTitElei.setColumns(15);
		tfTitElei.setDocument(new controleNumero(12));
		GridBagConstraints gbc_tfTitElei = new GridBagConstraints();
		gbc_tfTitElei.insets = new Insets(0, 0, 5, 5);
		gbc_tfTitElei.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfTitElei.gridx = 1;
		gbc_tfTitElei.gridy = 11;
		panelDados.add(tfTitElei, gbc_tfTitElei);

		lblZona = new JLabel("*Zona: ");
		lblZona.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblZona = new GridBagConstraints();
		gbc_lblZona.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblZona.insets = new Insets(0, 0, 5, 5);
		gbc_lblZona.gridx = 0;
		gbc_lblZona.gridy = 12;
		panelDados.add(lblZona, gbc_lblZona);

		tfZona = new JFormattedTextField();
		tfZona.setColumns(15);
		tfZona.setDocument(new controleNumero(4));
		GridBagConstraints gbc_tfZona = new GridBagConstraints();
		gbc_tfZona.insets = new Insets(0, 0, 5, 5);
		gbc_tfZona.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfZona.gridx = 1;
		gbc_tfZona.gridy = 12;
		panelDados.add(tfZona, gbc_tfZona);

		lblSecao = new JLabel("*Se\u00E7\u00E3o: ");
		lblSecao.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblSecao = new GridBagConstraints();
		gbc_lblSecao.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSecao.insets = new Insets(0, 0, 5, 5);
		gbc_lblSecao.gridx = 0;
		gbc_lblSecao.gridy = 13;
		panelDados.add(lblSecao, gbc_lblSecao);

		tfSecao = new JFormattedTextField();
		tfSecao.setColumns(15);
		tfSecao.setDocument(new controleNumero(4));
		GridBagConstraints gbc_tfSecao = new GridBagConstraints();
		gbc_tfSecao.insets = new Insets(0, 0, 5, 5);
		gbc_tfSecao.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfSecao.gridx = 1;
		gbc_tfSecao.gridy = 13;
		panelDados.add(tfSecao, gbc_tfSecao);

		tfConsAt = new JFormattedTextField();
		tfConsAt.setColumns(15);
		tfConsAt.setDocument(new controleLetra(30));
		GridBagConstraints gbc_tfConsAt = new GridBagConstraints();
		gbc_tfConsAt.gridwidth = 3;
		gbc_tfConsAt.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfConsAt.gridx = 1;
		gbc_tfConsAt.gridy = 18;
		panelDados.add(tfConsAt, gbc_tfConsAt);

		lblConsAt = new JLabel("*Consagra\u00E7\u00E3o atual:");
		lblConsAt.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblConsAt = new GridBagConstraints();
		gbc_lblConsAt.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblConsAt.insets = new Insets(0, 0, 0, 5);
		gbc_lblConsAt.gridx = 0;
		gbc_lblConsAt.gridy = 18;
		panelDados.add(lblConsAt, gbc_lblConsAt);

		lblConsagracao = new JLabel("Consagra\u00E7\u00E3o:");
		lblConsagracao.setForeground(Color.YELLOW);
		GridBagConstraints gbc_lblConsagracao = new GridBagConstraints();
		gbc_lblConsagracao.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblConsagracao.insets = new Insets(0, 0, 5, 5);
		gbc_lblConsagracao.gridx = 0;
		gbc_lblConsagracao.gridy = 15;
		panelDados.add(lblConsagracao, gbc_lblConsagracao);

		lblFiliacao = new JLabel("*Filia\u00E7\u00E3o: ");
		lblFiliacao.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblFiliacao = new GridBagConstraints();
		gbc_lblFiliacao.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblFiliacao.insets = new Insets(0, 0, 5, 5);
		gbc_lblFiliacao.gridx = 0;
		gbc_lblFiliacao.gridy = 16;
		panelDados.add(lblFiliacao, gbc_lblFiliacao);

		tfFiliacao = new JFormattedTextField();
		tfFiliacao.setColumns(15);
		tfFiliacao.setDocument(new controleLetra(80));
		GridBagConstraints gbc_tfFiliacao = new GridBagConstraints();
		gbc_tfFiliacao.gridwidth = 3;
		gbc_tfFiliacao.insets = new Insets(0, 0, 5, 0);
		gbc_tfFiliacao.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfFiliacao.gridx = 1;
		gbc_tfFiliacao.gridy = 16;
		panelDados.add(tfFiliacao, gbc_tfFiliacao);

		lblConsPret = new JLabel("Consagra\u00E7\u00E3o pretendida:");
		lblConsPret.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblConsPret = new GridBagConstraints();
		gbc_lblConsPret.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblConsPret.insets = new Insets(0, 0, 5, 5);
		gbc_lblConsPret.gridx = 0;
		gbc_lblConsPret.gridy = 17;
		panelDados.add(lblConsPret, gbc_lblConsPret);

		tfConsPret = new JFormattedTextField();
		tfConsPret.setColumns(15);
		tfConsPret.setDocument(new controleLetra(30));
		GridBagConstraints gbc_tfConsPret = new GridBagConstraints();
		gbc_tfConsPret.gridwidth = 3;
		gbc_tfConsPret.insets = new Insets(0, 0, 5, 0);
		gbc_tfConsPret.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfConsPret.gridx = 1;
		gbc_tfConsPret.gridy = 17;
		panelDados.add(tfConsPret, gbc_tfConsPret);

		panelDados2 = new JPanel();
		panelDados2.setOpaque(false);
		panelInserir.add(panelDados2, "cell 2 0,alignx left,aligny top");
		GridBagLayout gbl_panelDados2 = new GridBagLayout();
		gbl_panelDados2.columnWidths = new int[] { 82, 232, 0, 16, 0 };
		gbl_panelDados2.rowHeights = new int[] { 0, 20, 20, 20, 20, 0, 0, 0, 0, 0, 20, 20, 0 };
		gbl_panelDados2.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelDados2.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		panelDados2.setLayout(gbl_panelDados2);

		lblEndereço = new JLabel("Endere\u00E7o:");
		lblEndereço.setForeground(Color.YELLOW);
		GridBagConstraints gbc_lblEndereço = new GridBagConstraints();
		gbc_lblEndereço.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEndereço.gridwidth = 2;
		gbc_lblEndereço.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndereço.gridx = 0;
		gbc_lblEndereço.gridy = 0;
		panelDados2.add(lblEndereço, gbc_lblEndereço);

		lblCEP = new JLabel("*CEP:");
		lblCEP.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblCEP = new GridBagConstraints();
		gbc_lblCEP.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCEP.insets = new Insets(0, 0, 5, 5);
		gbc_lblCEP.gridx = 0;
		gbc_lblCEP.gridy = 1;
		panelDados2.add(lblCEP, gbc_lblCEP);

		tfCEP = new JFormattedTextField();
		tfCEP.setColumns(30);
		tfCEP.setDocument(new controleNumero(8));
		GridBagConstraints gbc_tfCEP = new GridBagConstraints();
		gbc_tfCEP.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfCEP.insets = new Insets(0, 0, 5, 5);
		gbc_tfCEP.gridx = 1;
		gbc_tfCEP.gridy = 1;
		panelDados2.add(tfCEP, gbc_tfCEP);

		lblEstado = new JLabel("*Estado:");
		lblEstado.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblEstado = new GridBagConstraints();
		gbc_lblEstado.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEstado.insets = new Insets(0, 0, 5, 5);
		gbc_lblEstado.gridx = 0;
		gbc_lblEstado.gridy = 2;
		panelDados2.add(lblEstado, gbc_lblEstado);

		cbEstado = new JComboBox<Object>();
		cbEstado.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				cidades(cbEstado, cbCidade);
			}
		});
		cbEstado.setMaximumRowCount(28);
		cbEstado.setModel(new DefaultComboBoxModel<Object>(new String[] { "", "Acre", "Alagoas", "Amap\u00E1",
				"Amazonas", "Bahia", "Cear\u00E1", "Distrito Federal", "Esp\u00EDrito Santo", "Goi\u00E1s",
				"Maranh\u00E3o", "Mato Grosso", "Mato Grosso do Sul", "Minas Gerais", "Par\u00E1", "Para\u00EDba",
				"Paran\u00E1", "Pernambuco", "Piau\u00ED", "Rio de Janeiro", "Rio Grande do Norte", "Rio Grande do Sul",
				"Rond\u00F4nia", "Roraima", "Santa Catarina", "S\u00E3o Paulo", "Sergipe", "Tocantins" }));
		cbEstado.setSelectedIndex(0);
		GridBagConstraints gbc_cbEstado = new GridBagConstraints();
		gbc_cbEstado.anchor = GridBagConstraints.WEST;
		gbc_cbEstado.insets = new Insets(0, 0, 5, 5);
		gbc_cbEstado.gridx = 1;
		gbc_cbEstado.gridy = 2;
		panelDados2.add(cbEstado, gbc_cbEstado);

		lblCidade = new JLabel("*Cidade: ");
		lblCidade.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblCidade = new GridBagConstraints();
		gbc_lblCidade.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCidade.insets = new Insets(0, 0, 5, 5);
		gbc_lblCidade.gridx = 0;
		gbc_lblCidade.gridy = 3;
		panelDados2.add(lblCidade, gbc_lblCidade);

		cbCidade = new JComboBox<Object>();
		cbCidade.setMaximumRowCount(5565);
		GridBagConstraints gbc_cbCidade = new GridBagConstraints();
		gbc_cbCidade.anchor = GridBagConstraints.WEST;
		gbc_cbCidade.insets = new Insets(0, 0, 5, 5);
		gbc_cbCidade.gridx = 1;
		gbc_cbCidade.gridy = 3;
		panelDados2.add(cbCidade, gbc_cbCidade);

		lblComplemento = new JLabel("Complemento:");
		lblComplemento.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblComplemento = new GridBagConstraints();
		gbc_lblComplemento.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblComplemento.insets = new Insets(0, 0, 5, 5);
		gbc_lblComplemento.gridx = 0;
		gbc_lblComplemento.gridy = 4;
		panelDados2.add(lblComplemento, gbc_lblComplemento);

		tfComplemento = new JFormattedTextField();
		tfComplemento.setColumns(15);
		tfComplemento.setDocument(new controleLetra_Numero(200));
		GridBagConstraints gbc_tfComplemento = new GridBagConstraints();
		gbc_tfComplemento.insets = new Insets(0, 0, 5, 5);
		gbc_tfComplemento.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfComplemento.gridx = 1;
		gbc_tfComplemento.gridy = 4;
		panelDados2.add(tfComplemento, gbc_tfComplemento);

		lblBairro = new JLabel("Bairro:\r\n");
		lblBairro.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblBairro = new GridBagConstraints();
		gbc_lblBairro.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBairro.insets = new Insets(0, 0, 5, 5);
		gbc_lblBairro.gridx = 0;
		gbc_lblBairro.gridy = 5;
		panelDados2.add(lblBairro, gbc_lblBairro);

		tfBairro = new JFormattedTextField();
		tfBairro.setColumns(15);
		tfBairro.setDocument(new controleLetra(80));
		GridBagConstraints gbc_tfBairro = new GridBagConstraints();
		gbc_tfBairro.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfBairro.gridwidth = 3;
		gbc_tfBairro.insets = new Insets(0, 0, 5, 0);
		gbc_tfBairro.gridx = 1;
		gbc_tfBairro.gridy = 5;
		panelDados2.add(tfBairro, gbc_tfBairro);

		lblFamiliares = new JLabel("Familiares:");
		lblFamiliares.setForeground(Color.YELLOW);
		GridBagConstraints gbc_lblFamiliares = new GridBagConstraints();
		gbc_lblFamiliares.gridwidth = 2;
		gbc_lblFamiliares.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblFamiliares.insets = new Insets(0, 0, 5, 5);
		gbc_lblFamiliares.gridx = 0;
		gbc_lblFamiliares.gridy = 7;
		panelDados2.add(lblFamiliares, gbc_lblFamiliares);

		lblPai = new JLabel("*Nome do pai: ");
		lblPai.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblPai = new GridBagConstraints();
		gbc_lblPai.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPai.insets = new Insets(0, 0, 5, 5);
		gbc_lblPai.gridx = 0;
		gbc_lblPai.gridy = 8;
		panelDados2.add(lblPai, gbc_lblPai);

		tfPai = new JFormattedTextField();
		tfPai.setColumns(15);
		tfPai.setDocument(new controleLetra(80));
		GridBagConstraints gbc_tfPai = new GridBagConstraints();
		gbc_tfPai.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfPai.gridwidth = 3;
		gbc_tfPai.insets = new Insets(0, 0, 5, 0);
		gbc_tfPai.gridx = 1;
		gbc_tfPai.gridy = 8;
		panelDados2.add(tfPai, gbc_tfPai);

		lblnomeDaMe = new JLabel("*Nome da m\u00E3e:");
		lblnomeDaMe.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblnomeDaMe = new GridBagConstraints();
		gbc_lblnomeDaMe.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblnomeDaMe.insets = new Insets(0, 0, 5, 5);
		gbc_lblnomeDaMe.gridx = 0;
		gbc_lblnomeDaMe.gridy = 9;
		panelDados2.add(lblnomeDaMe, gbc_lblnomeDaMe);

		tfMae = new JFormattedTextField();
		tfMae.setColumns(15);
		tfMae.setDocument(new controleLetra(80));
		GridBagConstraints gbc_tfMae = new GridBagConstraints();
		gbc_tfMae.gridwidth = 3;
		gbc_tfMae.insets = new Insets(0, 0, 5, 0);
		gbc_tfMae.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfMae.gridx = 1;
		gbc_tfMae.gridy = 9;
		panelDados2.add(tfMae, gbc_tfMae);

		lblConjugue = new JLabel("Nome do(a) conjugue:");
		lblConjugue.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblConjugue = new GridBagConstraints();
		gbc_lblConjugue.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblConjugue.insets = new Insets(0, 0, 5, 5);
		gbc_lblConjugue.gridx = 0;
		gbc_lblConjugue.gridy = 10;
		panelDados2.add(lblConjugue, gbc_lblConjugue);

		tfConjugue = new JFormattedTextField();
		tfConjugue.setColumns(15);
		tfConjugue.setDocument(new controleLetra(80));
		GridBagConstraints gbc_tfConjugue = new GridBagConstraints();
		gbc_tfConjugue.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfConjugue.gridwidth = 3;
		gbc_tfConjugue.insets = new Insets(0, 0, 5, 0);
		gbc_tfConjugue.gridx = 1;
		gbc_tfConjugue.gridy = 10;
		panelDados2.add(tfConjugue, gbc_tfConjugue);

		lblTelConj = new JLabel("Telefone do(a) conjugue:");
		lblTelConj.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblTelConj = new GridBagConstraints();
		gbc_lblTelConj.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTelConj.insets = new Insets(0, 0, 0, 5);
		gbc_lblTelConj.gridx = 0;
		gbc_lblTelConj.gridy = 11;
		panelDados2.add(lblTelConj, gbc_lblTelConj);
		tfTelConj = new JFormattedTextField(new MaskFormatter("(##)#####-####"));
		tfTelConj.setText("(  )     -    ");
		tfTelConj.setColumns(15);
		GridBagConstraints gbc_tfTelConj = new GridBagConstraints();
		gbc_tfTelConj.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfTelConj.insets = new Insets(0, 0, 0, 5);
		gbc_tfTelConj.gridx = 1;
		gbc_tfTelConj.gridy = 11;
		panelDados2.add(tfTelConj, gbc_tfTelConj);

		panelFoto = new JPanel();
		panelFoto.setOpaque(false);
		panelInserir.add(panelFoto, "cell 4 0,alignx right,aligny top");
		panelFoto.setLayout(new MigLayout("", "[150.00px,fill][30px,center][10px,right][77px,grow,fill]",
				"[23px][200px,fill][77px,bottom]"));

		btnRegistrar = new JButton("Registrar");
		panelFoto.add(btnRegistrar, "cell 2 0 2 1,growx,aligny top");
		btnRegistrar.setBackground(Color.WHITE);
		btnRegistrar.setForeground(Color.WHITE);
		btnRegistrar.setEnabled(false);
		btnRegistrar.setOpaque(false);
		btnRegistrar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				btnRegistrar_actionPerformed(arg0);
			}
		});

		btnRegistrar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnRegistrar.doClick();
					e.consume();
					return;
				}
			}
		});

		txtpnParaAtivarO_1 = new JTextPane();
		panelFoto.add(txtpnParaAtivarO_1, "cell 3 1 1 2,grow");
		txtpnParaAtivarO_1.setEditable(false);
		txtpnParaAtivarO_1.setOpaque(false);
		txtpnParaAtivarO_1.setForeground(Color.WHITE);
		txtpnParaAtivarO_1.setFont(new Font("Tahoma", Font.BOLD, 11));

		painelImagem = new JPanel();
		painelImagem.setOpaque(false);
		painelImagem.setForeground(Color.BLACK);
		painelImagem.setBorder(new LineBorder(Color.WHITE, 2, true));
		panelFoto.add(painelImagem, "cell 0 0 2 2,grow");
		painelImagem.setLayout(new CardLayout(0, 0));

		lblImagem = new JLabel("");
		lblImagem.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					switch (e.getButton()) {
					case MouseEvent.BUTTON1:
						btnLimparTudo.doClick();
						break;
					case MouseEvent.BUTTON2:
						break;
					case MouseEvent.BUTTON3:
						break;
					default:
						break;
					}
				}
			}
		});
		painelImagem.add(lblImagem, "name_99357072250000");

		btnBuscarFot = new JButton("Buscar foto\r\n");
		panelFoto.add(btnBuscarFot, "cell 0 2 2 1,growx,aligny top");
		btnBuscarFot.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnBuscarFot.doClick();
					e.consume();
					return;
				}
			}
		});

		btnBuscarFot.setBackground(Color.WHITE);
		btnBuscarFot.setForeground(Color.WHITE);
		btnBuscarFot.setOpaque(false);
		btnBuscarFot.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				btnBuscar_actionPerformed(e);
			}
		});

		lblObsOsCampos = new JLabel(" Obs 3: os campos marcados com \" * \" s\u00E3o obrigat\u00F3rios.");
		panelInserir.add(lblObsOsCampos, "cell 0 3 2 1,alignx left,aligny bottom");
		lblObsOsCampos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblObsOsCampos.setForeground(Color.WHITE);

		JPanel panelAlterar = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Image image = getImage(fundo);

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
		panelAlterar.setForeground(Color.WHITE);
		panelAlterar.setOpaque(false);
		tabbedPane.addTab("Alterar", null, panelAlterar, null);
		panelAlterar.setLayout(
				new MigLayout("", "[521.00px,left][10.00px,center][521.00px,right][85.00px,fill][341.00px,grow,right]",
						"[315px,grow][558px][20px][]"));
		panelDadosAlt = new JPanel();
		panelDadosAlt.setOpaque(false);
		panelAlterar.add(panelDadosAlt, "cell 0 0 2 1,alignx left,aligny top");
		GridBagLayout gbl_panelDados1 = new GridBagLayout();
		gbl_panelDados1.columnWidths = new int[] { 82, 170, 0, 16, 0 };
		gbl_panelDados1.rowHeights = new int[] { 0, 20, 20, 20, 20, 0, 0, 0, 0, 20, 20, 20, 20, 0, 20, 0, 0, 0, 0 };
		gbl_panelDados1.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelDados1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		panelDadosAlt.setLayout(gbl_panelDados1);

		lblDadosPessoais = new JLabel("Dados pessoais:");
		lblDadosPessoais.setForeground(Color.YELLOW);
		GridBagConstraints gbc_lblDadosPessoais1 = new GridBagConstraints();
		gbc_lblDadosPessoais1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblDadosPessoais1.gridwidth = 2;
		gbc_lblDadosPessoais1.insets = new Insets(0, 0, 5, 5);
		gbc_lblDadosPessoais1.gridx = 0;
		gbc_lblDadosPessoais1.gridy = 0;
		panelDadosAlt.add(lblDadosPessoais, gbc_lblDadosPessoais1);

		JLabel lblnomeAlt = new JLabel("*Nome: ");
		GridBagConstraints gbc_lblnome1 = new GridBagConstraints();
		gbc_lblnome1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblnome1.insets = new Insets(0, 0, 5, 5);
		gbc_lblnome1.gridx = 0;
		gbc_lblnome1.gridy = 1;
		panelDadosAlt.add(lblnomeAlt, gbc_lblnome1);
		lblnomeAlt.setForeground(Color.WHITE);

		tfNomeAlt = new JFormattedTextField();
		tfNomeAlt.setColumns(30);
		GridBagConstraints gbc_tfNome1 = new GridBagConstraints();
		gbc_tfNome1.gridwidth = 3;
		gbc_tfNome1.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNome1.insets = new Insets(0, 0, 5, 0);
		gbc_tfNome1.gridx = 1;
		gbc_tfNome1.gridy = 1;
		panelDadosAlt.add(tfNomeAlt, gbc_tfNome1);
		tfNomeAlt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnBuscarAlt.doClick();
					e.consume();
					return;
				}
			}
		});
		tfNomeAlt.setDocument(new controleLetra(60));

		lblCpfAlt = new JLabel("*CPF: ");
		lblCpfAlt.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblCpfAlt = new GridBagConstraints();
		gbc_lblCpfAlt.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCpfAlt.insets = new Insets(0, 0, 5, 5);
		gbc_lblCpfAlt.gridx = 0;
		gbc_lblCpfAlt.gridy = 2;
		panelDadosAlt.add(lblCpfAlt, gbc_lblCpfAlt);

		tfCPFAlt = new JFormattedTextField();
		tfCPFAlt.setColumns(15);
		GridBagConstraints gbc_tfCPF1 = new GridBagConstraints();
		gbc_tfCPF1.gridwidth = 2;
		gbc_tfCPF1.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfCPF1.insets = new Insets(0, 0, 5, 5);
		gbc_tfCPF1.gridx = 1;
		gbc_tfCPF1.gridy = 2;
		panelDadosAlt.add(tfCPFAlt, gbc_tfCPF1);
		tfCPFAlt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					btnBuscarAlt.requestFocus();
					arg0.consume();
					return;
				}
			}
		});
		tfCPFAlt.setDocument(new controleNumero(11));

		lblRg = new JLabel("*RG: ");
		lblRg.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblRg1 = new GridBagConstraints();
		gbc_lblRg1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRg1.insets = new Insets(0, 0, 5, 5);
		gbc_lblRg1.gridx = 0;
		gbc_lblRg1.gridy = 3;
		panelDadosAlt.add(lblRg, gbc_lblRg1);

		tfRgAlt = new JFormattedTextField();
		tfRgAlt.setColumns(15);
		tfRgAlt.setDocument(new controleNumero(14));
		GridBagConstraints gbc_tfRg1 = new GridBagConstraints();
		gbc_tfRg1.insets = new Insets(0, 0, 5, 5);
		gbc_tfRg1.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfRg1.gridx = 1;
		gbc_tfRg1.gridy = 3;
		panelDadosAlt.add(tfRgAlt, gbc_tfRg1);

		lblNascimento = new JLabel("*Nascimento: \r\n");
		lblNascimento.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNascimento1 = new GridBagConstraints();
		gbc_lblNascimento1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNascimento1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNascimento1.gridx = 0;
		gbc_lblNascimento1.gridy = 4;
		panelDadosAlt.add(lblNascimento, gbc_lblNascimento1);
		try {
			tfNascimentoAlt = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		tfNascimentoAlt.setColumns(15);
		GridBagConstraints gbc_tfNascimento1 = new GridBagConstraints();
		gbc_tfNascimento1.insets = new Insets(0, 0, 5, 5);
		gbc_tfNascimento1.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNascimento1.gridx = 1;
		gbc_tfNascimento1.gridy = 4;
		panelDadosAlt.add(tfNascimentoAlt, gbc_tfNascimento1);
		try {
			tfTelefoneAlt = new JFormattedTextField(new MaskFormatter("(##)#####-####"));
			tfTelefoneAlt.setText("(  )     -    ");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		lblTelefoneAlt = new JLabel("*Telefone: ");
		lblTelefoneAlt.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblTelefoneAlt = new GridBagConstraints();
		gbc_lblTelefoneAlt.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTelefoneAlt.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefoneAlt.gridx = 0;
		gbc_lblTelefoneAlt.gridy = 5;
		panelDadosAlt.add(lblTelefoneAlt, gbc_lblTelefoneAlt);
		tfTelefoneAlt.setColumns(15);
		GridBagConstraints gbc_tfTelefone1 = new GridBagConstraints();
		gbc_tfTelefone1.insets = new Insets(0, 0, 5, 5);
		gbc_tfTelefone1.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfTelefone1.gridx = 1;
		gbc_tfTelefone1.gridy = 5;
		panelDadosAlt.add(tfTelefoneAlt, gbc_tfTelefone1);

		lblEmail = new JLabel("E-mail: ");
		lblEmail.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblEmail1 = new GridBagConstraints();
		gbc_lblEmail1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEmail1.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail1.gridx = 0;
		gbc_lblEmail1.gridy = 6;
		panelDadosAlt.add(lblEmail, gbc_lblEmail1);

		tfEmailAlt = new JFormattedTextField();
		tfEmailAlt.setColumns(15);
		GridBagConstraints gbc_tfEmail1 = new GridBagConstraints();
		gbc_tfEmail1.gridwidth = 3;
		gbc_tfEmail1.insets = new Insets(0, 0, 5, 0);
		gbc_tfEmail1.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfEmail1.gridx = 1;
		gbc_tfEmail1.gridy = 6;
		panelDadosAlt.add(tfEmailAlt, gbc_tfEmail1);

		lblEscolaridade = new JLabel("*Escolaridade: ");
		lblEscolaridade.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblEscolaridade1 = new GridBagConstraints();
		gbc_lblEscolaridade1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEscolaridade1.insets = new Insets(0, 0, 5, 5);
		gbc_lblEscolaridade1.gridx = 0;
		gbc_lblEscolaridade1.gridy = 7;
		panelDadosAlt.add(lblEscolaridade, gbc_lblEscolaridade1);

		tfEscolaridadeAlt = new JFormattedTextField();
		tfEscolaridadeAlt.setColumns(15);
		tfEscolaridadeAlt.setDocument(new controleLetra(50));
		GridBagConstraints gbc_tfEscolaridade1 = new GridBagConstraints();
		gbc_tfEscolaridade1.insets = new Insets(0, 0, 5, 5);
		gbc_tfEscolaridade1.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfEscolaridade1.gridx = 1;
		gbc_tfEscolaridade1.gridy = 7;
		panelDadosAlt.add(tfEscolaridadeAlt, gbc_tfEscolaridade1);

		lblEstCivil = new JLabel("Estado civil: ");
		lblEstCivil.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblEstCivil1 = new GridBagConstraints();
		gbc_lblEstCivil1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEstCivil1.insets = new Insets(0, 0, 5, 5);
		gbc_lblEstCivil1.gridx = 0;
		gbc_lblEstCivil1.gridy = 8;
		panelDadosAlt.add(lblEstCivil, gbc_lblEstCivil1);

		cbEstCivilAlt = new JComboBox<Object>();
		cbEstCivilAlt.setModel(new DefaultComboBoxModel<Object>(
				new String[] { "", "Solteiro(a)", "Casado(a)", "Separado(a)", "Divorciado(a)", "Vi\u00FAvo(a)" }));
		cbEstCivilAlt.setMaximumRowCount(5);
		GridBagConstraints gbc_cbEstCivil1 = new GridBagConstraints();
		gbc_cbEstCivil1.anchor = GridBagConstraints.WEST;
		gbc_cbEstCivil1.insets = new Insets(0, 0, 5, 5);
		gbc_cbEstCivil1.gridx = 1;
		gbc_cbEstCivil1.gridy = 8;
		panelDadosAlt.add(cbEstCivilAlt, gbc_cbEstCivil1);

		lblTtuloEleitoral = new JLabel("T\u00EDtulo Eleitoral:");
		lblTtuloEleitoral.setForeground(Color.YELLOW);
		GridBagConstraints gbc_lblTtuloEleitoral1 = new GridBagConstraints();
		gbc_lblTtuloEleitoral1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTtuloEleitoral1.gridwidth = 2;
		gbc_lblTtuloEleitoral1.insets = new Insets(0, 0, 5, 5);
		gbc_lblTtuloEleitoral1.gridx = 0;
		gbc_lblTtuloEleitoral1.gridy = 10;
		panelDadosAlt.add(lblTtuloEleitoral, gbc_lblTtuloEleitoral1);

		lblTitElei = new JLabel("*N\u00FAmero: \r\n ");
		lblTitElei.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblTitElei1 = new GridBagConstraints();
		gbc_lblTitElei1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTitElei1.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitElei1.gridx = 0;
		gbc_lblTitElei1.gridy = 11;
		panelDadosAlt.add(lblTitElei, gbc_lblTitElei1);

		tfTitEleiAlt = new JFormattedTextField();
		tfTitEleiAlt.setColumns(15);
		tfTitEleiAlt.setDocument(new controleNumero(12));
		GridBagConstraints gbc_tfTitElei1 = new GridBagConstraints();
		gbc_tfTitElei1.insets = new Insets(0, 0, 5, 5);
		gbc_tfTitElei1.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfTitElei1.gridx = 1;
		gbc_tfTitElei1.gridy = 11;
		panelDadosAlt.add(tfTitEleiAlt, gbc_tfTitElei1);

		lblZona = new JLabel("*Zona: ");
		lblZona.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblZona1 = new GridBagConstraints();
		gbc_lblZona1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblZona1.insets = new Insets(0, 0, 5, 5);
		gbc_lblZona1.gridx = 0;
		gbc_lblZona1.gridy = 12;
		panelDadosAlt.add(lblZona, gbc_lblZona1);

		tfZonaAlt = new JFormattedTextField();
		tfZonaAlt.setColumns(15);
		tfZonaAlt.setDocument(new controleNumero(4));
		GridBagConstraints gbc_tfZona1 = new GridBagConstraints();
		gbc_tfZona1.insets = new Insets(0, 0, 5, 5);
		gbc_tfZona1.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfZona1.gridx = 1;
		gbc_tfZona1.gridy = 12;
		panelDadosAlt.add(tfZonaAlt, gbc_tfZona1);

		lblSecao = new JLabel("*Se\u00E7\u00E3o: ");
		lblSecao.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblSecao1 = new GridBagConstraints();
		gbc_lblSecao1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSecao1.insets = new Insets(0, 0, 5, 5);
		gbc_lblSecao1.gridx = 0;
		gbc_lblSecao1.gridy = 13;
		panelDadosAlt.add(lblSecao, gbc_lblSecao1);

		tfSecaoAlt = new JFormattedTextField();
		tfSecaoAlt.setColumns(15);
		tfSecaoAlt.setDocument(new controleNumero(4));
		GridBagConstraints gbc_tfSecao1 = new GridBagConstraints();
		gbc_tfSecao1.insets = new Insets(0, 0, 5, 5);
		gbc_tfSecao1.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfSecao1.gridx = 1;
		gbc_tfSecao1.gridy = 13;
		panelDadosAlt.add(tfSecaoAlt, gbc_tfSecao1);

		tfConsAtAlt = new JFormattedTextField();
		tfConsAtAlt.setColumns(15);
		tfConsAtAlt.setDocument(new controleLetra(40));
		GridBagConstraints gbc_tfConsAt1 = new GridBagConstraints();
		gbc_tfConsAt1.gridwidth = 3;
		gbc_tfConsAt1.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfConsAt1.gridx = 1;
		gbc_tfConsAt1.gridy = 18;
		panelDadosAlt.add(tfConsAtAlt, gbc_tfConsAt1);

		lblConsAt = new JLabel("*Consagra\u00E7\u00E3o atual:");
		lblConsAt.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblConsAt1 = new GridBagConstraints();
		gbc_lblConsAt1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblConsAt1.insets = new Insets(0, 0, 0, 5);
		gbc_lblConsAt1.gridx = 0;
		gbc_lblConsAt1.gridy = 18;
		panelDadosAlt.add(lblConsAt, gbc_lblConsAt1);

		lblConsagracao = new JLabel("Consagra\u00E7\u00E3o:");
		lblConsagracao.setForeground(Color.YELLOW);
		GridBagConstraints gbc_lblConsagracao1 = new GridBagConstraints();
		gbc_lblConsagracao1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblConsagracao1.insets = new Insets(0, 0, 5, 5);
		gbc_lblConsagracao1.gridx = 0;
		gbc_lblConsagracao1.gridy = 15;
		panelDadosAlt.add(lblConsagracao, gbc_lblConsagracao1);

		lblFiliacao = new JLabel("*Filia\u00E7\u00E3o: ");
		lblFiliacao.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblFiliacao1 = new GridBagConstraints();
		gbc_lblFiliacao1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblFiliacao1.insets = new Insets(0, 0, 5, 5);
		gbc_lblFiliacao1.gridx = 0;
		gbc_lblFiliacao1.gridy = 16;
		panelDadosAlt.add(lblFiliacao, gbc_lblFiliacao1);

		tfFiliacaoAlt = new JFormattedTextField();
		tfFiliacaoAlt.setColumns(15);
		tfFiliacaoAlt.setDocument(new controleLetra(30));
		GridBagConstraints gbc_tfFiliacao1 = new GridBagConstraints();
		gbc_tfFiliacao1.gridwidth = 3;
		gbc_tfFiliacao1.insets = new Insets(0, 0, 5, 0);
		gbc_tfFiliacao1.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfFiliacao1.gridx = 1;
		gbc_tfFiliacao1.gridy = 16;
		panelDadosAlt.add(tfFiliacaoAlt, gbc_tfFiliacao1);

		lblConsPret = new JLabel("Consagra\u00E7\u00E3o pretendida:");
		lblConsPret.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblConsPret1 = new GridBagConstraints();
		gbc_lblConsPret1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblConsPret1.insets = new Insets(0, 0, 5, 5);
		gbc_lblConsPret1.gridx = 0;
		gbc_lblConsPret1.gridy = 17;
		panelDadosAlt.add(lblConsPret, gbc_lblConsPret1);

		tfConsPretAlt = new JFormattedTextField();
		tfConsPretAlt.setColumns(15);
		tfConsPretAlt.setDocument(new controleLetra(40));
		GridBagConstraints gbc_tfConsPret1 = new GridBagConstraints();
		gbc_tfConsPret1.gridwidth = 3;
		gbc_tfConsPret1.insets = new Insets(0, 0, 5, 0);
		gbc_tfConsPret1.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfConsPret1.gridx = 1;
		gbc_tfConsPret1.gridy = 17;
		panelDadosAlt.add(tfConsPretAlt, gbc_tfConsPret1);

		panelDadosAlt2 = new JPanel();
		panelDadosAlt2.setOpaque(false);
		panelAlterar.add(panelDadosAlt2, "cell 2 0,alignx left,aligny top");
		GridBagLayout gbl_panelDadosAlt2 = new GridBagLayout();
		gbl_panelDadosAlt2.columnWidths = new int[] { 82, 170, 0, 16, 0 };
		gbl_panelDadosAlt2.rowHeights = new int[] { 0, 20, 20, 20, 20, 0, 0, 0, 0, 0, 20, 20, 0 };
		gbl_panelDadosAlt2.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelDadosAlt2.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		panelDadosAlt2.setLayout(gbl_panelDadosAlt2);

		lblEndereço = new JLabel("Endere\u00E7o:");
		lblEndereço.setForeground(Color.YELLOW);
		GridBagConstraints gbc_lblEndereço1 = new GridBagConstraints();
		gbc_lblEndereço1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEndereço1.gridwidth = 2;
		gbc_lblEndereço1.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndereço1.gridx = 0;
		gbc_lblEndereço1.gridy = 0;
		panelDadosAlt2.add(lblEndereço, gbc_lblEndereço1);

		lblCEP = new JLabel("*CEP:");
		lblCEP.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblCEP1 = new GridBagConstraints();
		gbc_lblCEP1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCEP1.insets = new Insets(0, 0, 5, 5);
		gbc_lblCEP1.gridx = 0;
		gbc_lblCEP1.gridy = 1;
		panelDadosAlt2.add(lblCEP, gbc_lblCEP1);

		tfCEPAlt = new JFormattedTextField();
		tfCEPAlt.setColumns(30);
		tfCEPAlt.setDocument(new controleNumero(8));
		GridBagConstraints gbc_tfCEPAlt = new GridBagConstraints();
		gbc_tfCEPAlt.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfCEPAlt.insets = new Insets(0, 0, 5, 5);
		gbc_tfCEPAlt.gridx = 1;
		gbc_tfCEPAlt.gridy = 1;
		panelDadosAlt2.add(tfCEPAlt, gbc_tfCEPAlt);

		lblEstado = new JLabel("*Estado:");
		lblEstado.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblEstado1 = new GridBagConstraints();
		gbc_lblEstado1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEstado1.insets = new Insets(0, 0, 5, 5);
		gbc_lblEstado1.gridx = 0;
		gbc_lblEstado1.gridy = 2;
		panelDadosAlt2.add(lblEstado, gbc_lblEstado1);

		cbEstadoAlt = new JComboBox<Object>();
		cbEstadoAlt.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				cidades(cbEstadoAlt, cbCidadeAlt);
			}
		});
		cbEstadoAlt.setMaximumRowCount(28);
		cbEstadoAlt.setModel(new DefaultComboBoxModel<Object>(new String[] { "", "Acre", "Alagoas", "Amap\u00E1",
				"Amazonas", "Bahia", "Cear\u00E1", "Distrito Federal", "Esp\u00EDrito Santo", "Goi\u00E1s",
				"Maranh\u00E3o", "Mato Grosso", "Mato Grosso do Sul", "Minas Gerais", "Par\u00E1", "Para\u00EDba",
				"Paran\u00E1", "Pernambuco", "Piau\u00ED", "Rio de Janeiro", "Rio Grande do Norte", "Rio Grande do Sul",
				"Rond\u00F4nia", "Roraima", "Santa Catarina", "S\u00E3o Paulo", "Sergipe", "Tocantins" }));
		cbEstadoAlt.setSelectedIndex(0);
		GridBagConstraints gbc_cbEstadoAlt = new GridBagConstraints();
		gbc_cbEstadoAlt.anchor = GridBagConstraints.WEST;
		gbc_cbEstadoAlt.insets = new Insets(0, 0, 5, 5);
		gbc_cbEstadoAlt.gridx = 1;
		gbc_cbEstadoAlt.gridy = 2;
		panelDadosAlt2.add(cbEstadoAlt, gbc_cbEstadoAlt);

		lblCidade = new JLabel("*Cidade: ");
		lblCidade.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblCidade1 = new GridBagConstraints();
		gbc_lblCidade1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCidade1.insets = new Insets(0, 0, 5, 5);
		gbc_lblCidade1.gridx = 0;
		gbc_lblCidade1.gridy = 3;
		panelDadosAlt2.add(lblCidade, gbc_lblCidade1);

		cbCidadeAlt = new JComboBox<Object>();
		cbCidadeAlt.setMaximumRowCount(5565);
		GridBagConstraints gbc_cbCidadeAlt = new GridBagConstraints();
		gbc_cbCidadeAlt.anchor = GridBagConstraints.WEST;
		gbc_cbCidadeAlt.insets = new Insets(0, 0, 5, 5);
		gbc_cbCidadeAlt.gridx = 1;
		gbc_cbCidadeAlt.gridy = 3;
		panelDadosAlt2.add(cbCidadeAlt, gbc_cbCidadeAlt);

		lblCompleAlt = new JLabel("Complemento:");
		lblCompleAlt.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblCompleAlt = new GridBagConstraints();
		gbc_lblCompleAlt.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCompleAlt.insets = new Insets(0, 0, 5, 5);
		gbc_lblCompleAlt.gridx = 0;
		gbc_lblCompleAlt.gridy = 4;
		panelDadosAlt2.add(lblCompleAlt, gbc_lblCompleAlt);

		tfComplementoAlt = new JFormattedTextField();
		tfComplementoAlt.setColumns(15);
		tfComplementoAlt.setDocument(new controleLetra_Numero(200));
		GridBagConstraints gbc_tfComplementoAlt = new GridBagConstraints();
		gbc_tfComplementoAlt.insets = new Insets(0, 0, 5, 5);
		gbc_tfComplementoAlt.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfComplementoAlt.gridx = 1;
		gbc_tfComplementoAlt.gridy = 4;
		panelDadosAlt2.add(tfComplementoAlt, gbc_tfComplementoAlt);

		lblBairro = new JLabel("Bairro:\r\n");
		lblBairro.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblBairro1 = new GridBagConstraints();
		gbc_lblBairro1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBairro1.insets = new Insets(0, 0, 5, 5);
		gbc_lblBairro1.gridx = 0;
		gbc_lblBairro1.gridy = 5;
		panelDadosAlt2.add(lblBairro, gbc_lblBairro1);

		tfBairroAlt = new JFormattedTextField();
		tfBairroAlt.setColumns(15);
		tfBairroAlt.setDocument(new controleLetra(80));
		GridBagConstraints gbc_tfBairroAlt = new GridBagConstraints();
		gbc_tfBairroAlt.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfBairroAlt.gridwidth = 3;
		gbc_tfBairroAlt.insets = new Insets(0, 0, 5, 0);
		gbc_tfBairroAlt.gridx = 1;
		gbc_tfBairroAlt.gridy = 5;
		panelDadosAlt2.add(tfBairroAlt, gbc_tfBairroAlt);

		lblFamiliares = new JLabel("Familiares:");
		lblFamiliares.setForeground(Color.YELLOW);
		GridBagConstraints gbc_lblFamiliares1 = new GridBagConstraints();
		gbc_lblFamiliares1.gridwidth = 2;
		gbc_lblFamiliares1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblFamiliares1.insets = new Insets(0, 0, 5, 5);
		gbc_lblFamiliares1.gridx = 0;
		gbc_lblFamiliares1.gridy = 7;
		panelDadosAlt2.add(lblFamiliares, gbc_lblFamiliares1);

		lblPai = new JLabel("*Nome do pai: ");
		lblPai.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblPai1 = new GridBagConstraints();
		gbc_lblPai1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPai1.insets = new Insets(0, 0, 5, 5);
		gbc_lblPai1.gridx = 0;
		gbc_lblPai1.gridy = 8;
		panelDadosAlt2.add(lblPai, gbc_lblPai1);

		tfPaiAlt = new JFormattedTextField();
		tfPaiAlt.setColumns(15);
		tfPaiAlt.setDocument(new controleLetra(80));
		GridBagConstraints gbc_tfPaiAlt = new GridBagConstraints();
		gbc_tfPaiAlt.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfPaiAlt.gridwidth = 3;
		gbc_tfPaiAlt.insets = new Insets(0, 0, 5, 0);
		gbc_tfPaiAlt.gridx = 1;
		gbc_tfPaiAlt.gridy = 8;
		panelDadosAlt2.add(tfPaiAlt, gbc_tfPaiAlt);

		lblnomeDaMe = new JLabel("*Nome da m\u00E3e:");
		lblnomeDaMe.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblnomeDaMe1 = new GridBagConstraints();
		gbc_lblnomeDaMe1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblnomeDaMe1.insets = new Insets(0, 0, 5, 5);
		gbc_lblnomeDaMe1.gridx = 0;
		gbc_lblnomeDaMe1.gridy = 9;
		panelDadosAlt2.add(lblnomeDaMe, gbc_lblnomeDaMe1);

		tfMaeAlt = new JFormattedTextField();
		tfMaeAlt.setColumns(15);
		tfMaeAlt.setDocument(new controleLetra(80));
		GridBagConstraints gbc_tfMaeAlt = new GridBagConstraints();
		gbc_tfMaeAlt.gridwidth = 3;
		gbc_tfMaeAlt.insets = new Insets(0, 0, 5, 0);
		gbc_tfMaeAlt.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfMaeAlt.gridx = 1;
		gbc_tfMaeAlt.gridy = 9;
		panelDadosAlt2.add(tfMaeAlt, gbc_tfMaeAlt);

		lblConjugue = new JLabel("Nome do(a) conjugue:");
		lblConjugue.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblConjugue1 = new GridBagConstraints();
		gbc_lblConjugue1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblConjugue1.insets = new Insets(0, 0, 5, 5);
		gbc_lblConjugue1.gridx = 0;
		gbc_lblConjugue1.gridy = 10;
		panelDadosAlt2.add(lblConjugue, gbc_lblConjugue1);

		tfConjugueAlt = new JFormattedTextField();
		tfConjugueAlt.setColumns(15);
		tfConjugueAlt.setDocument(new controleLetra(80));
		GridBagConstraints gbc_tfConjugueAlt = new GridBagConstraints();
		gbc_tfConjugueAlt.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfConjugueAlt.gridwidth = 3;
		gbc_tfConjugueAlt.insets = new Insets(0, 0, 5, 0);
		gbc_tfConjugueAlt.gridx = 1;
		gbc_tfConjugueAlt.gridy = 10;
		panelDadosAlt2.add(tfConjugueAlt, gbc_tfConjugueAlt);

		lblTelConj = new JLabel("Telefone do(a) conjugue:");
		lblTelConj.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblTelConj1 = new GridBagConstraints();
		gbc_lblTelConj1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTelConj1.insets = new Insets(0, 0, 0, 5);
		gbc_lblTelConj1.gridx = 0;
		gbc_lblTelConj1.gridy = 11;
		panelDadosAlt2.add(lblTelConj, gbc_lblTelConj1);
		try {
			tfTelConjAlt = new JFormattedTextField(new MaskFormatter("(##)#####-####"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		tfTelConjAlt.setColumns(15);
		GridBagConstraints gbc_tfTelConjAlt = new GridBagConstraints();
		gbc_tfTelConjAlt.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfTelConjAlt.insets = new Insets(0, 0, 0, 5);
		gbc_tfTelConjAlt.gridx = 1;
		gbc_tfTelConjAlt.gridy = 11;
		panelDadosAlt2.add(tfTelConjAlt, gbc_tfTelConjAlt);

		panelFotoAlt = new JPanel();
		panelFotoAlt.setOpaque(false);
		panelAlterar.add(panelFotoAlt, "cell 4 0,alignx right,aligny top");
		panelFotoAlt.setLayout(new MigLayout("", "[150.00px,center][30.00px,center][10px,right][77px,grow,fill]",
				"[23.00px][23px][23px][154.00px,center][77px,grow,bottom]"));

		btnAlterar = new JButton("Alterar");
		panelFotoAlt.add(btnAlterar, "cell 2 0 2 1,growx,aligny top");
		btnAlterar.setBackground(Color.WHITE);
		btnAlterar.setForeground(Color.WHITE);
		btnAlterar.setEnabled(false);
		btnAlterar.setOpaque(false);
		btnAlterar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				btnAlterar_actionPerformed(arg0);
			}
		});

		btnAlterar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnAlterar.doClick();
					e.consume();
					return;
				}
			}
		});

		painelImagemAlt = new JPanel();
		panelFotoAlt.add(painelImagemAlt, "cell 0 0 2 4,grow");
		painelImagemAlt.setBorder(new LineBorder(Color.WHITE, 2, true));
		painelImagemAlt.setOpaque(false);

		lblImagemAlt = new JLabel();
		lblImagemAlt.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagemAlt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					switch (e.getButton()) {
					case MouseEvent.BUTTON1:
						btnLimparTudo.doClick();
						e.consume();
						break;
					case MouseEvent.BUTTON2:
						break;
					case MouseEvent.BUTTON3:
						break;
					default:
						break;
					}
				}
			}
		});
		painelImagemAlt.setLayout(new CardLayout(0, 0));
		painelImagemAlt.add(lblImagemAlt, "name_29937897275700");

		btnBuscarAlt = new JButton("Buscar");
		btnBuscarAlt.setOpaque(false);
		btnBuscarAlt.setForeground(Color.WHITE);
		btnBuscarAlt.setBackground(Color.WHITE);
		panelFotoAlt.add(btnBuscarAlt, "cell 2 1 2 1,growx,aligny top");
		btnBuscarAlt.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				btnBuscarAlt_actionPerformed(e);
			}
		});

		btnBuscarAlt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnBuscarAlt.doClick();
					e.consume();
					return;
				}
			}
		});

		btnImprimir = new JButton("Imprimir");
		btnImprimir.setOpaque(false);
		btnImprimir.setForeground(Color.WHITE);
		btnImprimir.setEnabled(false);
		btnImprimir.setBackground(Color.WHITE);

		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					imprimir();
				} catch (JRException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnImprimir.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnImprimir.doClick();
					e.consume();
					return;
				}
			}
		});
		panelFotoAlt.add(btnImprimir, "cell 2 2 2 1,growx,aligny top");

		txtpnParaAtivarO_1Alt = new JTextPane();
		panelFotoAlt.add(txtpnParaAtivarO_1Alt, "cell 3 3 1 2,grow");
		txtpnParaAtivarO_1Alt.setEditable(false);
		txtpnParaAtivarO_1Alt.setOpaque(false);
		txtpnParaAtivarO_1Alt.setForeground(Color.WHITE);
		txtpnParaAtivarO_1Alt.setFont(new Font("Tahoma", Font.BOLD, 11));

		btnBuscarFotAlt = new JButton("Buscar foto\r\n");
		panelFotoAlt.add(btnBuscarFotAlt, "cell 0 4 2 1,growx,aligny top");
		btnBuscarFotAlt.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				btnBuscarFotAlt_actionPerformed(e);
			}
		});

		btnBuscarFotAlt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnBuscarFotAlt.doClick();
					e.consume();
					return;
				}
			}
		});

		btnBuscarFotAlt.setBackground(Color.WHITE);
		btnBuscarFotAlt.setForeground(Color.WHITE);
		btnBuscarFotAlt.setOpaque(false);

		lblObsOsCamposAlt = new JLabel(" Obs 3: os campos marcados com \" * \" s\u00E3o obrigat\u00F3rios.");
		panelAlterar.add(lblObsOsCamposAlt, "cell 0 3 2 1,alignx left,aligny bottom");
		lblObsOsCamposAlt.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblObsOsCamposAlt.setForeground(Color.WHITE);
		contentPane.setLayout(new MigLayout("", "[482.00px,left][444px,grow,right]", "[25.00px][238px,grow,fill]"));
		contentPane.add(tabbedPane, "cell 0 1 2 1,grow");

		panelExcluir = new JPanel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Image image = getImage(fundo);

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
		panelExcluir.setToolTipText("");
		panelExcluir.setOpaque(false);
		panelExcluir.setForeground(Color.WHITE);
		tabbedPane.addTab("Ecluir", null, panelExcluir, null);
		panelExcluir
				.setLayout(new MigLayout("", "[424.00px,grow,left][424.00px,left][164.00px,fill][419.00px,grow,right]",
						"[347.00,grow][438.00][60.00,grow,bottom]"));

		panelDadosExcl = new JPanel();
		panelDadosExcl.setOpaque(false);
		panelExcluir.add(panelDadosExcl, "cell 0 0,alignx left,aligny top");
		GridBagLayout gbl_panelDadosExcl = new GridBagLayout();
		gbl_panelDadosExcl.columnWidths = new int[] { 82, 170, 0, 16, 0 };
		gbl_panelDadosExcl.rowHeights = new int[] { 0, 20, 0 };
		gbl_panelDadosExcl.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelDadosExcl.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panelDadosExcl.setLayout(gbl_panelDadosExcl);

		lblNomeExcl = new JLabel("Nome:");
		lblNomeExcl.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNomeExcl = new GridBagConstraints();
		gbc_lblNomeExcl.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNomeExcl.insets = new Insets(0, 0, 5, 5);
		gbc_lblNomeExcl.gridx = 0;
		gbc_lblNomeExcl.gridy = 0;
		panelDadosExcl.add(lblNomeExcl, gbc_lblNomeExcl);

		tfNomeExcl = new JFormattedTextField();
		tfNomeExcl.setColumns(30);
		tfNomeExcl.setDocument(new controleLetra(80));
		tfNomeExcl.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					btnBuscarExcl.doClick();
					arg0.consume();
					return;
				}
			}
		});
		GridBagConstraints gbc_tfNomeExcl = new GridBagConstraints();
		gbc_tfNomeExcl.gridwidth = 3;
		gbc_tfNomeExcl.insets = new Insets(0, 0, 5, 0);
		gbc_tfNomeExcl.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNomeExcl.gridx = 1;
		gbc_tfNomeExcl.gridy = 0;
		panelDadosExcl.add(tfNomeExcl, gbc_tfNomeExcl);

		lblCPFExcl = new JLabel("CPF: ");
		lblCPFExcl.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblCPFExcl = new GridBagConstraints();
		gbc_lblCPFExcl.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCPFExcl.insets = new Insets(0, 0, 0, 5);
		gbc_lblCPFExcl.gridx = 0;
		gbc_lblCPFExcl.gridy = 1;
		panelDadosExcl.add(lblCPFExcl, gbc_lblCPFExcl);

		tfCPFExcl = new JFormattedTextField();
		tfCPFExcl.setColumns(15);
		tfCPFExcl.setDocument(new controleNumero(11));
		tfCPFExcl.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					btnBuscarExcl.doClick();
					arg0.consume();
					return;
				}
			}
		});
		GridBagConstraints gbc_tfCPFExcl = new GridBagConstraints();
		gbc_tfCPFExcl.insets = new Insets(0, 0, 0, 5);
		gbc_tfCPFExcl.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfCPFExcl.gridx = 1;
		gbc_tfCPFExcl.gridy = 1;
		panelDadosExcl.add(tfCPFExcl, gbc_tfCPFExcl);

		panelFotoExcl = new JPanel();
		panelFotoExcl.setOpaque(false);
		panelExcluir.add(panelFotoExcl, "cell 3 0 1 2,alignx right,aligny top");
		panelFotoExcl.setLayout(new MigLayout("", "[150.00px,fill][30.00px,fill][10px,right][77px,grow,fill]",
				"[23px][23px][200px][77px,grow,bottom]"));

		painelImagemExlc = new JPanel();
		painelImagemExlc.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		painelImagemExlc.setOpaque(false);
		panelFotoExcl.add(painelImagemExlc, "cell 0 0 2 3,grow");
		painelImagemExlc.setLayout(new CardLayout(0, 0));

		lblImagemExcl = new JLabel();
		lblImagemExcl.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagemExcl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					switch (e.getButton()) {
					case MouseEvent.BUTTON1:
						btnLimparTudo.doClick();
						lblImagemExcl.setIcon(null);
						e.consume();
						break;
					case MouseEvent.BUTTON2:
						break;
					case MouseEvent.BUTTON3:
						break;
					default:
						break;
					}
				}
			}
		});
		painelImagemExlc.add(lblImagemExcl, "name_68936448706800");

		btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.setOpaque(false);
		btnExcluir.setForeground(Color.WHITE);
		btnExcluir.setBackground(Color.WHITE);

		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnExcluir_actionPerformed(e);
			}
		});

		btnExcluir.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnExcluir.doClick();
					e.consume();
					return;
				}
			}
		});
		panelFotoExcl.add(btnExcluir, "cell 2 0 2 1,growx,aligny top");

		btnBuscarExcl = new JButton("Buscar");
		btnBuscarExcl.setOpaque(false);
		btnBuscarExcl.setForeground(Color.WHITE);
		btnBuscarExcl.setBackground(Color.WHITE);

		btnBuscarExcl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBuscarExcl_actionPerformed(e);
			}
		});

		btnBuscarExcl.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnBuscarExcl.doClick();
					e.consume();
					return;
				}
			}
		});
		panelFotoExcl.add(btnBuscarExcl, "cell 2 1 2 1,growx,aligny top");

		txtpnObsParaAtivar = new JTextPane();
		txtpnObsParaAtivar.setOpaque(false);
		txtpnObsParaAtivar.setForeground(Color.WHITE);
		txtpnObsParaAtivar.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtpnObsParaAtivar.setEditable(false);
		panelFotoExcl.add(txtpnObsParaAtivar, "cell 3 2 1 2,alignx right,aligny top");

		lblObsExcl = new JLabel();
		lblObsExcl.setForeground(Color.WHITE);
		lblObsExcl.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelExcluir.add(lblObsExcl, "cell 0 2 2 1");
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		contentPane.add(panel, "cell 0 0 2 1,growx,aligny top");
		panel.setLayout(new BorderLayout(0, 0));

		panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_2.setOpaque(false);
		panel.add(panel_2, BorderLayout.WEST);

		lblUsurio = new JLabel();
		panel_2.add(lblUsurio);
		lblUsurio.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUsurio.setForeground(Color.WHITE);

		panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new MigLayout("", "[91px][91px][139px]", "[23px]"));

		btnLimparTudo = new JButton("Limpar tudo");
		btnLimparTudo.setOpaque(false);
		btnLimparTudo.setForeground(Color.WHITE);
		btnLimparTudo.setBackground(Color.WHITE);
		panel_1.add(btnLimparTudo, "cell 0 0");

		btnLimparTudo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				limparBean();

				apagar(tfNome, tfEmail, tfEscolaridade, cbEstCivil, tfFiliacao, tfConsPret, tfConsAt, tfComplemento,
						tfBairro, cbEstado, cbCidade, tfPai, tfMae, tfConjugue, tfCPF, tfRg, tfTelefone, tfTitElei,
						tfZona, tfSecao, tfCEP, tfTelConj, tfNascimento, image, lblImagem, btnBuscarFot);

				apagar(tfNomeAlt, tfEmailAlt, tfEscolaridadeAlt, cbEstCivilAlt, tfFiliacaoAlt, tfConsPretAlt,
						tfConsAtAlt, tfComplementoAlt, tfBairroAlt, cbEstadoAlt, cbCidadeAlt, tfPaiAlt, tfMaeAlt,
						tfConjugueAlt, tfCPFAlt, tfRgAlt, tfTelefoneAlt, tfTitEleiAlt, tfZonaAlt, tfSecaoAlt, tfCEPAlt,
						tfTelConjAlt, tfNascimentoAlt, image, lblImagemAlt, btnBuscarFotAlt);

				tfNomeExcl.setText(null);
				tfCPFExcl.setText(null);
				lblImagemExcl.setIcon(null);
				btnExcluir.setEnabled(false);
				btnRegistrar.setEnabled(false);
				btnAlterar.setEnabled(false);
				btnImprimir.setEnabled(false);
			}
		});

		btnLimparTudo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnLimparTudo.doClick();
					e.consume();
					return;
				}
			}
		});

		btnDeslogar = new JButton("Deslogar-se");
		panel_1.add(btnDeslogar, "cell 1 0,alignx left,aligny top");
		btnDeslogar.setBackground(Color.WHITE);
		btnDeslogar.setForeground(Color.WHITE);
		btnDeslogar.setOpaque(false);
		esconderDicas();
		tglbtnDesativarDicas = new JToggleButton("Ativar/Desativar dicas");
		tglbtnDesativarDicas.setSelected(true);
		panel_1.add(tglbtnDesativarDicas, "cell 2 0,alignx left,aligny top");
		tglbtnDesativarDicas.setBackground(Color.WHITE);
		tglbtnDesativarDicas.setForeground(Color.WHITE);
		tglbtnDesativarDicas.setOpaque(false);
		tglbtnDesativarDicas.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (tglbtnDesativarDicas.isSelected()) {
					esconderDicas();
				} else {
					mostrarDicas();
				}
			}
		});
		tglbtnDesativarDicas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					tglbtnDesativarDicas.doClick();
					e.consume();
					return;
				}
			}
		});

		btnDeslogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDeslogar_actionPerformed(e);
			}
		});

		btnDeslogar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnDeslogar.doClick();
					e.consume();
					return;
				}
			}
		});
	}

	// meus métodos

	protected void dadosLogin() {
		loginNome = ConexaoBEAN.getNome();
		lblUsurio.setText("Usu\u00E1rio: " + loginNome);
	}

	private void imprimir() throws JRException {

		dao.conectar();

		if (dao.conectou == true) {

				if (dao.imprimirPDF(bean) == false) {
					JOptionPane.showMessageDialog(null, "Nao foi possivel imprimir o formulário.", "Erro!",
							JOptionPane.ERROR_MESSAGE);

					dao.fecharCon();
					return;
				}

		} else {
			JOptionPane.showMessageDialog(null, "Nao foi possivel conectar ao Banco de Dados.", "Erro!",
					JOptionPane.ERROR_MESSAGE);
			btnLimparTudo.doClick();
			dao.fecharCon();
			return;
		}
	}

	private void cidades(JComboBox<Object> cbEstado, JComboBox<Object> cbCidade) {

		cbCidade.removeAllItems();
		cbCidade.setSelectedItem("");

		dao.conectar();

		if (dao.conectou == true) {

			bean.setIndiceEstado(cbEstado.getSelectedItem().toString().trim());

			if (dao.buscarCidade(bean) == true) {

				for (String c : bean.getCidades()) {
					cbCidade.addItem(c);
				}
			}

		} else {
			JOptionPane.showMessageDialog(null, "Nao foi possivel conectar ao Banco de Dados.", "Erro!",
					JOptionPane.ERROR_MESSAGE);
			btnLimparTudo.doClick();
			return;
		}
	}

	private File selecionarImagem() {

		fileChooser = new JFileChooser();
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos JPG & PNG",
				new String[] { "jpg", "png" });
		fileChooser.addChoosableFileFilter(filtro);
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
		fileChooser.showOpenDialog(this);

		return fileChooser.getSelectedFile();
	}

	private void btnBuscar_actionPerformed(ActionEvent arg0) {

		imagem = selecionarImagem();
		image = abrirImagem.abrirImageml(imagem, fileChooser.getSelectedFile(), imgIcon, image);

		if (image == null) {
			return;
		} else {
			lblImagem = ManipularImagem.exibiImagemLabel(image, lblImagem);
			btnRegistrar.setEnabled(true);
			btnRegistrar.requestFocus();
		}

	}

	private void btnBuscarFotAlt_actionPerformed(ActionEvent arg0) {

		imagem = selecionarImagem();
		image = abrirImagem.abrirImageml(imagem, fileChooser.getSelectedFile(), imgIcon, image);

		if (image.equals(null)) {
			return;
		} else {
			lblImagemAlt = ManipularImagem.exibiImagemLabel(image, lblImagemAlt);
			btnAlterar.setEnabled(true);
			btnAlterar.requestFocus();
			btnImprimir.setEnabled(true);
		}

	}

	protected void btnExcluir_actionPerformed(ActionEvent e) {

		limparBean();

		dao.conectar();

		if (dao.conectou == true) {

			if (tfCPFExcl.getText().isEmpty()) {

				btnExcluir.setEnabled(false);
				tfNomeExcl.setText(null);
				tfCPFExcl.requestFocus();
				dao.fecharCon();
				JOptionPane.showMessageDialog(null, "O CPF do membro à ser excluído do sistema não pode estar vazio!",
						"Erro!", JOptionPane.ERROR_MESSAGE);
				return;

			} else {

				int i = JOptionPane.showConfirmDialog(null, "Descadastrar este membro?", "Confirmar descadastro!",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (i == JOptionPane.YES_OPTION) {

					bean.setCpf(tfCPFExcl.getText());
					dao.excluir(bean);
					dao.fecharCon();
					btnLimparTudo.doClick();
					JOptionPane.showMessageDialog(null, "Membro descadastrado!", "Sucesso!",
							JOptionPane.INFORMATION_MESSAGE);
					return;

				} else {

					dao.fecharCon();
					return;
				}
			}

		} else {

			btnLimparTudo.doClick();
			limparBean();
			JOptionPane.showMessageDialog(null, "Nao foi possivel conectar ao Banco de Dados.", "Erro!",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	protected void btnBuscarAlt_actionPerformed(ActionEvent e) {

		dao.conectar();

		if (dao.conectou == true) {

			if (tfCPFAlt.getText().isEmpty() && tfNomeAlt.getText().isEmpty()) {

				tfNomeAlt.requestFocus();
				JOptionPane.showMessageDialog(null, "Preencha o CPF ou o nome do membro corretamente!", "Atenção!",
						JOptionPane.WARNING_MESSAGE);

			} else if (!tfCPFAlt.getText().isEmpty() && tfNomeAlt.getText().isEmpty()) {

				if (tfCPFAlt.getText().length() < 11) {

					tfCPFAlt.setText(null);
					tfCPFAlt.requestFocus();
					JOptionPane.showMessageDialog(null, "Preencha o CPF corretamente!", "Atenção!",
							JOptionPane.WARNING_MESSAGE);
					return;

				} else {

					limparBean();
					bean.setCpf(tfCPFAlt.getText());

					if (dao.buscarCPF(bean) == true) {
						try {
							buscarDados();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						btnAlterar.setEnabled(true);
						btnImprimir.setEnabled(true);
						return;

					} else {

						limparBean();
						dao.fecharCon();
						tfCPFAlt.setText(null);
						tfCPFAlt.requestFocus();
						JOptionPane.showMessageDialog(null, "Este membro não está cadastrado!", "Erro!",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			} else if (!tfNomeAlt.getText().isEmpty() && tfCPFAlt.getText().isEmpty()) {

				limparBean();
				bean.setNome(tfNomeAlt.getText());

				if (dao.buscarNome(bean) == true) {

					try {
						buscarDados();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					btnAlterar.setEnabled(true);
					btnImprimir.setEnabled(true);
					return;

				} else {
					limparBean();
					dao.fecharCon();
					tfNomeAlt.setText(null);
					tfNomeAlt.requestFocus();
					JOptionPane.showMessageDialog(null, "Este membro não está cadastrado!", "Erro!",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			} else if (!tfNomeAlt.getText().trim().isEmpty() && !tfCPFAlt.getText().trim().isEmpty()) {

				btnLimparTudo.doClick();
				tfNomeAlt.requestFocus();
				dao.fecharCon();
				JOptionPane.showMessageDialog(null, "Preencha somente ou campo CPF ou o campo Nome!", "Atenção!",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

		} else {
			btnLimparTudo.doClick();
			JOptionPane.showMessageDialog(null, "Nao foi possivel conectar ao Banco de Dados.", "Erro!",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	protected void btnBuscarExcl_actionPerformed(ActionEvent e) {

		dao.conectar();

		if (dao.conectou == true) {

			if (tfCPFExcl.getText().isEmpty() && tfNomeExcl.getText().isEmpty()) {

				JOptionPane.showMessageDialog(null, "Preencha o CPF ou o nome do membro corretamente!", "Atenção!",
						JOptionPane.WARNING_MESSAGE);

			} else if (!tfCPFExcl.getText().isEmpty() && tfNomeExcl.getText().isEmpty()) {

				if (tfCPFExcl.getText().length() < 11) {

					tfCPFExcl.setText(null);
					tfCPFExcl.requestFocus();
					JOptionPane.showMessageDialog(null, "Preencha o CPF corretamente!", "Atenção!",
							JOptionPane.WARNING_MESSAGE);
					return;

				} else {

					limparBean();
					bean.setCpf(tfCPFExcl.getText());

					if (dao.buscarCPF(bean) == true) {
						try {
							buscarDadosExcl();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						btnExcluir.setEnabled(true);
						btnExcluir.requestFocus();
						return;

					} else {
						limparBean();
						dao.fecharCon();
						tfCPFExcl.setText(null);
						tfCPFExcl.requestFocus();
						JOptionPane.showMessageDialog(null, "Este membro não está cadastrado!.", "Erro!",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			} else if (!tfNomeExcl.getText().isEmpty() && tfCPFExcl.getText().isEmpty()) {

				limparBean();
				bean.setNome(tfNomeExcl.getText());

				if (dao.buscarNome(bean) == true) {

					try {
						buscarDadosExcl();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					btnExcluir.setEnabled(true);
					btnExcluir.requestFocus();
					return;

				} else {

					limparBean();
					dao.fecharCon();
					tfNomeExcl.setText(null);
					tfNomeExcl.requestFocus();
					JOptionPane.showMessageDialog(null, "Este membro não está cadastrado!.", "Erro!",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			} else if (!tfNomeExcl.getText().trim().isEmpty() && !tfCPFExcl.getText().trim().isEmpty()) {

				limparBean();
				dao.fecharCon();
				tfNomeExcl.setText(null);
				tfCPFExcl.setText(null);
				tfNomeExcl.requestFocus();
				JOptionPane.showMessageDialog(null, "Preencha somento o campo CPF ou o campo Nome!", "Atenção!",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

		} else {

			btnLimparTudo.doClick();
			JOptionPane.showMessageDialog(null, "Nao foi possivel conectar ao Banco de Dados.", "Erro!",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	private void buscarDadosExcl() throws IOException {

		tfNomeExcl.setText(ConexaoBEAN.getNome());
		tfCPFExcl.setText(bean.getCpf());
		InputStream in = new ByteArrayInputStream(bean.getImagem());
		image = ImageIO.read(in);
		lblImagemExcl = ManipularImagem.exibiImagemLabel(image, lblImagemExcl);
	}

	private void buscarDados() throws IOException {

		tfNomeAlt.setText(ConexaoBEAN.getNome());
		tfEmailAlt.setText(bean.getEmail());
		tfEscolaridadeAlt.setText(bean.getEscolaridade());
		cbEstCivilAlt.setSelectedItem(bean.getEstadoCivil());
		tfFiliacaoAlt.setText(bean.getFiliacao());
		tfConsPretAlt.setText(bean.getConsPreten());
		tfConsAtAlt.setText(bean.getConsAtual());
		tfComplementoAlt.setText(bean.getComplemento());
		tfBairroAlt.setText(bean.getBairro());
		cbEstadoAlt.setSelectedItem(bean.getEstado());
		cbCidadeAlt.setSelectedItem(bean.getCidade());
		tfPaiAlt.setText(bean.getPai());
		tfMaeAlt.setText(bean.getMae());
		tfConjugueAlt.setText(bean.getConjugue());
		tfCPFAlt.setText(bean.getCpf());
		tfRgAlt.setText(bean.getRg());
		tfTelefoneAlt.setText(bean.getTelefone());
		tfTitEleiAlt.setText(bean.getTituloElei());
		tfZonaAlt.setText(bean.getZona());
		tfSecaoAlt.setText(bean.getSecao());
		tfCEPAlt.setText(bean.getCep());
		tfTelConjAlt.setText(bean.getTelConjugue());
		tfNascimentoAlt.setText(bean.getDataNasc());
		InputStream in = new ByteArrayInputStream(bean.getImagem());
		image = ImageIO.read(in);
		lblImagemAlt = ManipularImagem.exibiImagemLabel(image, lblImagemAlt);
	}

	private void btnRegistrar_actionPerformed(ActionEvent e) {

		dao.conectar();

		if (dao.conectou == true) {

			if (tfCPF.getText().isEmpty()) {

				btnRegistrar.setEnabled(false);
				JOptionPane.showMessageDialog(null, "O CPF do membro à ser excluído do sistema não pode estar vazio!",
						"Erro!", JOptionPane.ERROR_MESSAGE);
				btnLimparTudo.doClick();
				tfNome.requestFocus();
				dao.fecharCon();
				return;

			} else {

				int i = JOptionPane.showConfirmDialog(null, "Registar este novo membro?", "Confirmar registro!",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (i == JOptionPane.YES_OPTION) {

					limparBean();

					try {
						if (inserirBean(tfNome, tfEmail, tfEscolaridade, cbEstCivil, tfFiliacao, tfConsPret, tfConsAt,
								tfComplemento, tfBairro, cbEstado, cbCidade, tfPai, tfMae, tfConjugue, tfCPF, tfRg,
								tfTelefone, tfTitElei, tfZona, tfSecao, tfCEP, tfTelConj, tfNascimento, image,
								lblImagem, btnBuscarFot) == true) {

							if (dao.verificarUsuario(bean) == true) {

								if (dao.inserir(bean) == true) {

									btnLimparTudo.doClick();
									dao.fecharCon();
									JOptionPane.showMessageDialog(null, "Dados registrados!", "Sucesso!",
											JOptionPane.INFORMATION_MESSAGE);
									return;
								}

							} else if (dao.verificarUsuario(bean) == false && ConexaoBEAN.getNome().equals("sim")) {

								dao.fecharCon();
								limparBean();
								JOptionPane.showMessageDialog(null, "Nome já cadastrado!.", "Erro!",
										JOptionPane.ERROR_MESSAGE);
								return;

							} else if (dao.verificarUsuario(bean) == false && bean.getEmail().equals("sim")) {

								limparBean();
								dao.fecharCon();
								JOptionPane.showMessageDialog(null, "E-mail já cadastrado!.", "Erro!",
										JOptionPane.ERROR_MESSAGE);
								return;

							} else if (dao.verificarUsuario(bean) == false && bean.getTituloElei().equals("sim")) {

								limparBean();
								dao.fecharCon();
								JOptionPane.showMessageDialog(null, "Número do título eleitoral já cadastrado!.",
										"Erro!", JOptionPane.ERROR_MESSAGE);
								return;

							} else if (dao.verificarUsuario(bean) == false && bean.getCpf().equals("sim")) {

								limparBean();
								dao.fecharCon();
								JOptionPane.showMessageDialog(null, "CPF já cadastrado!.", "Erro!",
										JOptionPane.ERROR_MESSAGE);
								return;

							} else if (dao.verificarUsuario(bean) == false && bean.getRg().equals("sim")) {

								limparBean();
								dao.fecharCon();
								JOptionPane.showMessageDialog(null, "RG já cadastrado!.", "Erro!",
										JOptionPane.ERROR_MESSAGE);
								return;

							} else {
								limparBean();
								dao.fecharCon();
								JOptionPane.showMessageDialog(null,
										"Erro ao cadastrar membro! Verifique a conexão com o banco de dados", "Erro!",
										JOptionPane.ERROR_MESSAGE);
								return;
							}

						} else {
							limparBean();
							dao.fecharCon();
							JOptionPane.showMessageDialog(null,
									"Nao foi possivel enviar as informações ao Banco de Dados.", "Erro!",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
					} catch (HeadlessException | ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {

					limparBean();
					dao.fecharCon();
					return;
				}

			}

		} else {

			limparBean();
			dao.fecharCon();
			JOptionPane.showMessageDialog(null, "Nao foi possivel conectar ao Banco de Dados.", "Erro!",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	private boolean inserirBean(JFormattedTextField tfNome, JFormattedTextField tfEmail,
			JFormattedTextField tfEscolaridade, JComboBox<Object> cbEstCivil, JFormattedTextField tfFiliacao,
			JFormattedTextField tfConsPret, JFormattedTextField tfConsAt, JFormattedTextField tfComplemento,
			JFormattedTextField tfBairro, JComboBox<Object> cbEstado, JComboBox<Object> cbCidade,
			JFormattedTextField tfPai, JFormattedTextField tfMae, JFormattedTextField tfConjugue,
			JFormattedTextField tfCPF, JFormattedTextField tfRg, JFormattedTextField tfTelefone,
			JFormattedTextField tfTitElei, JFormattedTextField tfZona, JFormattedTextField tfSecao,
			JFormattedTextField tfCEP, JFormattedTextField tfTelConj, JFormattedTextField tfNascimento,
			BufferedImage image, JLabel lblImagem, JButton btnBuscar) throws ParseException {

		if (!tfNome.getText().isEmpty() && !tfEscolaridade.getText().isEmpty() && !tfFiliacao.getText().isEmpty()
				&& !tfConsAt.getText().isEmpty() && cbEstado.getSelectedIndex() != 0 && cbEstado.getSelectedIndex() != 0
				&& !tfPai.getText().isEmpty() && !tfMae.getText().isEmpty() && !tfCPF.getText().isEmpty()
				&& !tfRg.getText().isEmpty() && !tfTelefone.getText().isEmpty() && !tfTitElei.getText().isEmpty()
				&& !tfZona.getText().isEmpty() && !tfSecao.getText().isEmpty() && !tfCEP.getText().isEmpty()
				&& !tfNascimento.getText().isEmpty() && lblImagem.getIcon() != null) {

			if (cbEstCivil.getSelectedIndex() == 2 && tfConjugue.getText().isEmpty()) {

				JOptionPane.showMessageDialog(null, "Informe o nome do(a) conjugue corretamente!", "Atenção!",
						JOptionPane.WARNING_MESSAGE);
				tfConjugue.requestFocus();
				return false;

			} else if (!tfConjugue.getText().isEmpty() && cbEstCivil.getSelectedIndex() != 2) {

				cbEstCivil.setSelectedIndex(2);

			} else if (!tfTelConj.getText().isEmpty() && tfConjugue.getText().isEmpty()) {

				tfTelConj.setText(null);
			}

			String nome = tfNome.getText().trim();
			String email = tfEmail.getText().trim();
			String escolaridade = tfEscolaridade.getText().trim();
			String estadoCivil = cbEstCivil.getSelectedItem().toString();
			String filiacao = tfFiliacao.getText().trim();
			String consPret = tfConsPret.getText().trim();
			String consAt = tfConsAt.getText().trim();
			String complemento = tfComplemento.getText().trim();
			String bairro = tfBairro.getText().trim();
			String estado = cbEstado.getSelectedItem().toString();
			String cidade = cbCidade.getSelectedItem().toString();
			String pai = tfPai.getText().trim();
			String mae = tfMae.getText().trim();
			String conjugue = tfConjugue.getText().trim();
			String CPF = tfCPF.getText().trim();
			String RG = tfRg.getText().trim();
			String tel = tfTelefone.getText().trim();
			String titEleit = tfTitElei.getText().trim();
			String zona = tfZona.getText().trim();
			String secao = tfSecao.getText().trim();
			String CEP = tfCEP.getText().trim().trim();
			String telConjugue = tfTelConj.getText().trim();
			String nascimento = tfNascimento.getText().trim();
			byte[] img = ManipularImagem.getImgBytes(image);

			if (CPF.length() < 11) {

				JOptionPane.showMessageDialog(null, "Preencha o CPF corretamente!", "Atenção!",
						JOptionPane.WARNING_MESSAGE);
				tfCPF.setText(null);
				tfCPF.requestFocus();
				return false;

			} else if (RG.length() < 7) {

				JOptionPane.showMessageDialog(null, "Preencha o RG corretamente!", "Atenção!",
						JOptionPane.WARNING_MESSAGE);
				tfRg.setText(null);
				tfRg.requestFocus();
				return false;

			} else if (tel.length() < 14) {

				JOptionPane.showMessageDialog(null, "Preencha o número de telefone corretamente!", "Atenção!",
						JOptionPane.WARNING_MESSAGE);
				tfTelefone.setText(null);
				tfTelefone.requestFocus();
				return false;

			} else if (titEleit.length() < 12) {

				JOptionPane.showMessageDialog(null, "Preencha o número do título eleitoral corretamente!", "Atenção!",
						JOptionPane.WARNING_MESSAGE);
				tfTitElei.setText(null);
				tfTitElei.requestFocus();
				return false;

			} else if (zona.length() < 4) {

				JOptionPane.showMessageDialog(null, "Preencha o número da zona corretamente!", "Atenção!",
						JOptionPane.WARNING_MESSAGE);
				tfZona.setText(null);
				tfZona.requestFocus();
				return false;

			} else if (secao.length() < 4) {

				JOptionPane.showMessageDialog(null, "Preencha o número da seção corretamente!", "Atenção!",
						JOptionPane.WARNING_MESSAGE);
				tfSecao.setText(null);
				tfSecao.requestFocus();
				return false;

			} else if (CEP.length() < 8) {

				JOptionPane.showMessageDialog(null, "Preencha o CEP corretamente!", "Atenção!",
						JOptionPane.WARNING_MESSAGE);
				tfCEP.setText(null);
				tfCEP.requestFocus();
				return false;

			} else if (!telConjugue.contains("+") && !telConjugue.contains("(") && !telConjugue.contains(")")
					&& !telConjugue.contains("-") && telConjugue.length() > 17) {

				JOptionPane.showMessageDialog(null, "Preencha o número de telefone do(a) conjugue corretamente!",
						"Atenção!", JOptionPane.WARNING_MESSAGE);
				tfTelConj.setText(null);
				tfTelConj.requestFocus();
				return false;

			} else if (img.equals(null)) {

				JOptionPane.showMessageDialog(null, "Selecione a foto de perfil do membro!", "Atenção!",
						JOptionPane.WARNING_MESSAGE);
				lblImagem.equals(null);
				btnBuscar.requestFocus();
				return false;

			} else {

				bean.setBairro(bairro);
				bean.setCep(CEP);
				bean.setCidade(cidade);
				bean.setComplemento(complemento);
				bean.setConjugue(conjugue);
				bean.setConsAtual(consAt);
				bean.setConsPreten(consPret);
				bean.setCpf(CPF);
				bean.setDataNasc(nascimento);
				bean.setEmail(email);
				bean.setEscolaridade(escolaridade);
				bean.setEstado(estado);
				bean.setEstadoCivil(estadoCivil);
				bean.setFiliacao(filiacao);
				bean.setImagem(img);
				bean.setMae(mae);
				bean.setNome(nome);
				bean.setPai(pai);
				bean.setRg(RG);
				bean.setSecao(secao);
				bean.setTelConjugue(telConjugue);
				bean.setTelefone(tel);
				bean.setTituloElei(titEleit);
				bean.setZona(zona);

				return true;
			}

		} else {

			JOptionPane.showMessageDialog(null, "Preencha os campos corretamente!", "Atenção!",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
	}

	private void btnDeslogar_actionPerformed(ActionEvent e) {

		int i2 = JOptionPane.showConfirmDialog(null, "Fazer logoff?", "Deslogar", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE);

		if (i2 == JOptionPane.YES_OPTION) {

			btnLimparTudo.doClick();

			try {
				if (dao.conectar().isReadOnly()) {
					dao.fecharCon();
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			windowClosing();
		} else
			return;
	}

	private void btnAlterar_actionPerformed(ActionEvent e) {

		dao.conectar();

		if (dao.conectou == true) {

			if (tfCPFAlt.getText().isEmpty()) {

				btnLimparTudo.doClick();
				tfNomeAlt.requestFocus();
				dao.fecharCon();
				btnAlterar.setEnabled(false);
				btnImprimir.setEnabled(false);
				JOptionPane.showMessageDialog(null, "O CPF do membro a ser alterado não pode estar vazio!", "Erro!",
						JOptionPane.ERROR_MESSAGE);
				return;

			} else {

				limparBean();

				int i = JOptionPane.showConfirmDialog(null, "Atualizar membro?", "Confirmar atualização!",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (i == JOptionPane.YES_OPTION) {

					try {

						if (inserirBean(tfNomeAlt, tfEmailAlt, tfEscolaridadeAlt, cbEstCivilAlt, tfFiliacaoAlt,
								tfConsPretAlt, tfConsAtAlt, tfComplementoAlt, tfBairroAlt, cbEstadoAlt, cbCidadeAlt,
								tfPaiAlt, tfMaeAlt, tfConjugueAlt, tfCPFAlt, tfRgAlt, tfTelefoneAlt, tfTitEleiAlt,
								tfZonaAlt, tfSecaoAlt, tfCEPAlt, tfTelConjAlt, tfNascimentoAlt, image, lblImagemAlt,
								btnBuscarFotAlt) == true) {

							if (dao.alterar(bean) == true) {

								btnLimparTudo.doClick();
								dao.fecharCon();
								JOptionPane.showMessageDialog(null, "Dados atualizados com sucesso!", "Sucesso!",
										JOptionPane.INFORMATION_MESSAGE);
								return;

							} else {

								limparBean();
								dao.fecharCon();
								JOptionPane.showMessageDialog(null,
										"Erro ao atualizar membro! Verifique a conexão com o banco de dados", "Erro!",
										JOptionPane.ERROR_MESSAGE);
								return;
							}

						} else {

							limparBean();
							dao.fecharCon();
							JOptionPane.showMessageDialog(null,
									"Nao foi possivel enviar as informações ao Banco de Dados.", "Erro!",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
					} catch (HeadlessException | ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {

					limparBean();
					dao.fecharCon();
					return;
				}

			}

		} else {

			limparBean();
			dao.fecharCon();
			JOptionPane.showMessageDialog(null, "Nao foi possivel conectar ao Banco de Dados.", "Erro!",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	private void windowClosing() {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login l = new login();
					l.setVisible(true);
					dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void esconderDicas() {
		txtpnParaAtivarO_1Alt.setText(null);
		txtpnParaAtivarO_1.setText(null);
		lblObsOsCamposAlt.setText(null);
		lblObsOsCampos.setText(null);
		lblObsExcl.setText(null);
		txtpnObsParaAtivar.setText(null);
	}

	private void mostrarDicas() {
		txtpnParaAtivarO_1Alt.setText(dica6);
		txtpnParaAtivarO_1.setText(dica1);
		lblObsExcl.setText(dica2);
		lblObsOsCamposAlt.setText(dica3);
		lblObsOsCampos.setText(dica3);
		txtpnObsParaAtivar.setText(dica4);
	}

	private static void apagar(JFormattedTextField tfNome, JFormattedTextField tfEmail,
			JFormattedTextField tfEscolaridade, JComboBox<Object> cbEstCivil, JFormattedTextField tfFiliacao,
			JFormattedTextField tfConsPret, JFormattedTextField tfConsAt, JFormattedTextField tfComplemento,
			JFormattedTextField tfBairro, JComboBox<Object> cbEstado, JComboBox<Object> cbCidade,
			JFormattedTextField tfPai, JFormattedTextField tfMae, JFormattedTextField tfConjugue,
			JFormattedTextField tfCPF, JFormattedTextField tfRg, JFormattedTextField tfTelefone,
			JFormattedTextField tfTitElei, JFormattedTextField tfZona, JFormattedTextField tfSecao,
			JFormattedTextField tfCEP, JFormattedTextField tfTelConj, JFormattedTextField tfNascimento,
			BufferedImage image, JLabel lblImagem, JButton btnBuscar) {

		tfNome.setText(null);
		tfEmail.setText(null);
		tfEscolaridade.setText(null);
		cbEstCivil.setSelectedIndex(-1);
		tfFiliacao.setText(null);
		tfConsPret.setText(null);
		tfConsAt.setText(null);
		tfComplemento.setText(null);
		tfBairro.setText(null);
		cbEstado.setSelectedIndex(0);
		cbCidade.setSelectedIndex(-1);
		tfPai.setText(null);
		tfMae.setText(null);
		tfConjugue.setText(null);
		tfCPF.setText(null);
		tfRg.setText(null);
		tfTelefone.setText(null);
		tfTitElei.setText(null);
		tfZona.setText(null);
		tfSecao.setText(null);
		tfCEP.setText(null);
		tfTelConj.setText(null);
		tfNascimento.setText(null);
		image = null;
		lblImagem.setIcon(null);
		btnBuscar.requestFocus();
	}

	private void limparBean() {

		bean.setBairro(null);
		bean.setCep(null);
		bean.setCidade(null);
		bean.setComplemento(null);
		bean.setConjugue(null);
		bean.setConsAtual(null);
		bean.setConsPreten(null);
		bean.setCpf(null);
		bean.setDataNasc(null);
		bean.setEmail(null);
		bean.setEscolaridade(null);
		bean.setEstado(null);
		bean.setEstadoCivil(null);
		bean.setFiliacao(null);
		bean.setImagem(null);
		bean.setMae(null);
		bean.setNome(null);
		bean.setPai(null);
		bean.setRg(null);
		bean.setSecao(null);
		bean.setTelConjugue(null);
		bean.setTelefone(null);
		bean.setTituloElei(null);
		bean.setZona(null);
		bean.setSenhaHash(null);
		bean.setCidades(null);
		bean.setIndiceEstado(null);
	}
}
