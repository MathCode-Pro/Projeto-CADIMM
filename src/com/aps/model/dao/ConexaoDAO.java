package com.aps.model.dao;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aps.model.bean.ConexaoBEAN;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ConexaoDAO {

	private Connection con;
	private PreparedStatement stmt;
	public boolean conectou;

	public Connection conectar() {

		try {

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cadimm", "root", "");

			if (con != null) {

				conectou = true;

			} else {

				conectou = false;
			}

			return con;

		} catch (ClassNotFoundException e) {

			System.out.println("O driver expecificado nao foi encontrado.");
			return null;

		} catch (SQLException e) {
			conectou = false;
			return null;
		}
	}

	public boolean verificarUsuario(ConexaoBEAN dados) {

		String consultaCPF = "SELECT cpf FROM usuario WHERE cpf=?";

		try {

			stmt = con.prepareStatement(consultaCPF);
			stmt.setString(1, dados.getCpf());

			ResultSet res = stmt.executeQuery();

			if (res.next()) {

				dados.setCpf("sim");
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;

		}

		String consultaRG = "SELECT rg FROM usuario WHERE rg=?";

		try {

			stmt = con.prepareStatement(consultaRG);
			stmt.setString(1, dados.getRg());

			ResultSet res = stmt.executeQuery();

			if (res.next()) {

				dados.setRg("sim");
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;

		}

		String consultaEmail = "SELECT email FROM usuario WHERE email=?";

		try {

			stmt = con.prepareStatement(consultaEmail);
			stmt.setString(1, dados.getEmail());

			ResultSet res = stmt.executeQuery();

			if (res.next()) {

				dados.setEmail("sim");
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;

		}

		String consultaNome = "SELECT nome FROM usuario WHERE nome=?";

		try {

			stmt = con.prepareStatement(consultaNome);
			stmt.setString(1, ConexaoBEAN.getNome());

			ResultSet res = stmt.executeQuery();

			if (res.next()) {

				dados.setNome("sim");
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;

		}

		String consultaTitElei = "SELECT tituloEleitor FROM usuario WHERE tituloEleitor=?";

		try {

			stmt = con.prepareStatement(consultaTitElei);
			stmt.setString(1, dados.getTituloElei());

			ResultSet res = stmt.executeQuery();

			if (res.next()) {

				dados.setTituloElei("sim");
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;

		}

		return true;
	}

	public boolean inserir(ConexaoBEAN dados) {

		String sql = "INSERT INTO usuario(nome,cpf,rg,nascimento,telefone,email,"
				+ "tituloEleitor,zona,secao,escolaridade,estadoCivil,filiacao,consagracaoPretendida,consagracaoAtual)"
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {

			// tabela usuário
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ConexaoBEAN.getNome());
			stmt.setString(2, dados.getCpf());
			stmt.setString(3, dados.getRg());
			stmt.setString(4, dados.getDataNasc());
			stmt.setString(5, dados.getTelefone());
			stmt.setString(6, dados.getEmail());
			stmt.setString(7, dados.getTituloElei());
			stmt.setString(8, dados.getZona());
			stmt.setString(9, dados.getSecao());
			stmt.setString(10, dados.getEscolaridade());
			stmt.setString(11, dados.getEstadoCivil());
			stmt.setString(12, dados.getFiliacao());
			stmt.setString(13, dados.getConsPreten());
			stmt.setString(14, dados.getConsAtual());

			stmt.executeUpdate();

			// tablea endereço
			String sql2 = "INSERT INTO endereco(complemento,bairro,cidade,estado,"
					+ "cep,endereco_usuario) VALUES (?,?,?,?,?,?)";

			stmt = con.prepareStatement(sql2);
			stmt.setString(1, dados.getComplemento());
			stmt.setString(2, dados.getBairro());
			stmt.setString(3, dados.getCidade());
			stmt.setString(4, dados.getEstado());
			stmt.setString(5, dados.getCep());
			stmt.setString(6, dados.getCpf());

			stmt.executeUpdate();

			// tabela família
			String sql3 = "INSERT INTO familia(pai,mae,conjugue,telConjugue,familia_usuario) VALUES (?,?,?,?,?)";

			stmt = con.prepareStatement(sql3);
			stmt.setString(1, dados.getPai());
			stmt.setString(2, dados.getMae());
			stmt.setString(3, dados.getConjugue());
			stmt.setString(4, dados.getTelConjugue());
			stmt.setString(5, dados.getCpf());

			stmt.executeUpdate();

			// tabela perfil
			String sql4 = "INSERT INTO perfil(foto,perfil_usuario) VALUES (?,?)";

			stmt = con.prepareStatement(sql4);
			stmt.setBytes(1, dados.getImagem());
			stmt.setString(2, dados.getCpf());

			stmt.executeUpdate();

			stmt.close();

			return true;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean buscarCidade(ConexaoBEAN dados) {

		String sql = ("SELECT id FROM estado WHERE nome=?");

		try {
			int id = 0;

			stmt = con.prepareStatement(sql);

			stmt.setString(1, dados.getIndiceEstado());

			ResultSet res = stmt.executeQuery();

			while (res.next()) {

				id = res.getInt("id");

			}

			stmt.clearParameters();

			String sql2 = ("SELECT nome FROM cidade WHERE estado=?");

			stmt = con.prepareStatement(sql2);

			stmt.setInt(1, id);

			ResultSet res2 = stmt.executeQuery();

			List<String> lista = new ArrayList<String>();

			while (res2.next()) {

				lista.add(res2.getString("nome"));

			}

			dados.setCidades(lista);

			stmt.close();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean excluir(ConexaoBEAN dados) {

		try {

			String sql = "DELETE FROM usuario WHERE cpf=?";

			stmt = con.prepareStatement(sql);

			stmt.setString(1, dados.getCpf());

			stmt.executeUpdate();
			stmt.close();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;

		}

	}

	public boolean alterar(ConexaoBEAN dados) {

		String sql = "UPDATE usuario u INNER JOIN endereco e ON e.endereco_usuario = u.cpf "
				+ "INNER JOIN familia f ON f.familia_usuario = u.cpf "
				+ "INNER JOIN perfil p ON p.perfil_usuario = u.cpf "
				+ "SET u.nome=?, u.nascimento=?, u.telefone=?, u.email=?, "
				+ "u.tituloEleitor=?, u.zona=?, u.secao=?, u. escolaridade=?, u.estadoCivil=?, "
				+ "u.filiacao=?, u.consagracaoPretendida=?, u.consagracaoAtual=?, "
				+ "e.complemento=?, e.bairro=?, e.cidade=?, e.estado=?, e.cep=?, "
				+ "f.pai=?, f.mae=?, f.conjugue=?, f.telConjugue=?, p.foto=? WHERE u.cpf=?";

		try {
			stmt = con.prepareStatement(sql);

			stmt.setString(1, ConexaoBEAN.getNome());
			stmt.setString(2, dados.getDataNasc());
			stmt.setString(3, dados.getTelefone());
			stmt.setString(4, dados.getEmail());
			stmt.setString(5, dados.getTituloElei());
			stmt.setString(6, dados.getZona());
			stmt.setString(7, dados.getSecao());
			stmt.setString(8, dados.getEscolaridade());
			stmt.setString(9, dados.getEstadoCivil());
			stmt.setString(10, dados.getFiliacao());
			stmt.setString(11, dados.getConsPreten());
			stmt.setString(12, dados.getConsAtual());
			stmt.setString(13, dados.getComplemento());
			stmt.setString(14, dados.getBairro());
			stmt.setString(15, dados.getCidade());
			stmt.setString(16, dados.getEstado());
			stmt.setString(17, dados.getCep());
			stmt.setString(18, dados.getPai());
			stmt.setString(19, dados.getMae());
			stmt.setString(20, dados.getConjugue());
			stmt.setString(21, dados.getTelConjugue());
			stmt.setBytes(22, dados.getImagem());
			stmt.setString(23, dados.getCpf());

			stmt.executeUpdate();
			stmt.close();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean buscarCPF(ConexaoBEAN dados) {

		String sql = ("SELECT " + "endereco.`complemento` AS complemento, " + "endereco.`bairro` AS bairro, "
				+ "endereco.`cidade` AS cidade, " + "endereco.`estado` AS estado, " + "endereco.`cep` AS cep, "
				+ "familia.`pai` AS pai, " + "familia.`mae` AS mae, " + "familia.`conjugue` AS conjugue, "
				+ "familia.`telConjugue` AS telConjugue, " + "perfil.`foto` AS foto, " + "usuario.`rg` AS rg, "
				+ "usuario.`nome` AS nome, " + "usuario.`nascimento` AS nascimento, "
				+ "usuario.`telefone` AS telefone, " + "usuario.`email` AS email, "
				+ "usuario.`tituloEleitor` AS tituloEleitor, " + "usuario.`zona` AS zona, "
				+ "usuario.`secao` AS secao, " + "usuario.`escolaridade` AS escolaridade, "
				+ "usuario.`estadoCivil` AS estadoCivil, " + "usuario.`filiacao` AS filiacao, "
				+ "usuario.`consagracaoPretendida` AS consagracaoPretendida, "
				+ "usuario.`consagracaoAtual` AS consagracaoAtual " + "FROM "
				+ "`usuario` usuario INNER JOIN `endereco` endereco ON usuario.`cpf` = endereco.`endereco_usuario` "
				+ "INNER JOIN `familia` familia ON usuario.`cpf` = familia.`familia_usuario` "
				+ "INNER JOIN `perfil` perfil ON usuario.`cpf` = perfil.`perfil_usuario` " + "WHERE usuario.cpf = ?");

		try {
			stmt = con.prepareStatement(sql);

			stmt.setString(1, dados.getCpf());

			ResultSet res = stmt.executeQuery();

			while (res.next()) {

				dados.setRg(res.getString("rg"));
				dados.setNome(res.getString("nome"));
				dados.setDataNasc(res.getString("nascimento"));
				dados.setTelefone(res.getString("telefone"));
				dados.setEmail(res.getString("email"));
				dados.setTituloElei(res.getString("tituloEleitor"));
				dados.setZona(res.getString("zona"));
				dados.setSecao(res.getString("secao"));
				dados.setEscolaridade(res.getString("escolaridade"));
				dados.setEstadoCivil(res.getString("estadoCivil"));
				dados.setFiliacao(res.getString("filiacao"));
				dados.setConsPreten(res.getString("consagracaoPretendida"));
				dados.setConsAtual(res.getString("consagracaoAtual"));
				dados.setImagem(res.getBytes("foto"));
				dados.setPai(res.getString("pai"));
				dados.setMae(res.getString("mae"));
				dados.setConjugue(res.getString("conjugue"));
				dados.setTelConjugue(res.getString("telConjugue"));
				dados.setComplemento(res.getString("complemento"));
				dados.setBairro(res.getString("bairro"));
				dados.setCidade(res.getString("cidade"));
				dados.setEstado(res.getString("estado"));
				dados.setCep(res.getString("cep"));

				return true;
			}

			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return false;
	}

	public boolean buscarNome(ConexaoBEAN dados) {

		String sql = ("SELECT " + "endereco.`complemento` AS complemento, " + "endereco.`bairro` AS bairro, "
				+ "endereco.`cidade` AS cidade, " + "endereco.`estado` AS estado, " + "endereco.`cep` AS cep, "
				+ "familia.`pai` AS pai, " + "familia.`mae` AS mae, " + "familia.`conjugue` AS conjugue, "
				+ "familia.`telConjugue` AS telConjugue, " + "perfil.`foto` AS foto, " + "usuario.`rg` AS rg, "
				+ "usuario.`cpf` AS cpf, " + "usuario.`nascimento` AS nascimento, " + "usuario.`telefone` AS telefone, "
				+ "usuario.`email` AS email, " + "usuario.`tituloEleitor` AS tituloEleitor, "
				+ "usuario.`zona` AS zona, " + "usuario.`secao` AS secao, " + "usuario.`escolaridade` AS escolaridade, "
				+ "usuario.`estadoCivil` AS estadoCivil, " + "usuario.`filiacao` AS filiacao, "
				+ "usuario.`consagracaoPretendida` AS consagracaoPretendida, "
				+ "usuario.`consagracaoAtual` AS consagracaoAtual " + "FROM "
				+ "`usuario` usuario INNER JOIN `endereco` endereco ON usuario.`cpf` = endereco.`endereco_usuario` "
				+ "INNER JOIN `familia` familia ON usuario.`cpf` = familia.`familia_usuario` "
				+ "INNER JOIN `perfil` perfil ON usuario.`cpf` = perfil.`perfil_usuario` " + "WHERE usuario.nome = ?");

		try {
			stmt = con.prepareStatement(sql);

			stmt.setString(1, ConexaoBEAN.getNome());

			ResultSet res = stmt.executeQuery();

			while (res.next()) {

				dados.setCpf(res.getString("cpf"));
				dados.setRg(res.getString("rg"));
				dados.setDataNasc(res.getString("nascimento"));
				dados.setTelefone(res.getString("telefone"));
				dados.setEmail(res.getString("email"));
				dados.setTituloElei(res.getString("tituloEleitor"));
				dados.setZona(res.getString("zona"));
				dados.setSecao(res.getString("secao"));
				dados.setEscolaridade(res.getString("escolaridade"));
				dados.setEstadoCivil(res.getString("estadoCivil"));
				dados.setFiliacao(res.getString("filiacao"));
				dados.setConsPreten(res.getString("consagracaoPretendida"));
				dados.setConsAtual(res.getString("consagracaoAtual"));
				dados.setImagem(res.getBytes("foto"));
				dados.setPai(res.getString("pai"));
				dados.setMae(res.getString("mae"));
				dados.setConjugue(res.getString("conjugue"));
				dados.setTelConjugue(res.getString("telConjugue"));
				dados.setComplemento(res.getString("complemento"));
				dados.setBairro(res.getString("bairro"));
				dados.setCidade(res.getString("cidade"));
				dados.setEstado(res.getString("estado"));
				dados.setCep(res.getString("cep"));

				return true;
			}

			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return false;
	}

	public boolean consultarADM(ConexaoBEAN dados) {

		String sql = ("SELECT nome FROM adm WHERE cpf=?");

		try {
			stmt = con.prepareStatement(sql);

			stmt.setString(1, dados.getSenhaHash());

			ResultSet res = stmt.executeQuery();

			while (res.next()) {

				dados.setNome(res.getString("nome"));

				return true;
			}

			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;

	}

	public boolean imprimirPDF(ConexaoBEAN dados) {

		try {

			String src = "report1.jrxml";

			JasperDesign jd = JRXmlLoader.load(src);

			String sql = "SELECT " + "endereco.`complemento` AS endereco_complemento, " + "endereco.`bairro` AS endereco_bairro, "
					+ "endereco.`cidade` AS endereco_cidade, " + "endereco.`estado` AS endereco_estado, " + "endereco.`cep` AS endereco_cep, "
					+ "familia.`pai` AS familia_pai, " + "familia.`mae` AS familia_mae, " + "familia.`conjugue` AS familia_conjugue, "
					+ "familia.`telConjugue` AS familia_telConjugue, " + "usuario.`rg` AS usuario_rg, "
					+ "usuario.`cpf` AS usuario_cpf, " + "usuario.`nome` AS usuario_nome, " + "usuario.`nascimento` AS usuario_nascimento, "
					+ "usuario.`telefone` AS usuario_telefone, " + "usuario.`email` AS usuario_email, "
					+ "usuario.`tituloEleitor` AS usuario_tituloEleitor, " + "usuario.`zona` AS usuario_zona, "
					+ "usuario.`secao` AS usuario_secao, " + "usuario.`escolaridade` AS usuario_escolaridade, "
					+ "usuario.`estadoCivil` AS usuario_estadoCivil, " + "usuario.`filiacao` AS usuario_filiacao, "
					+ "usuario.`consagracaoPretendida` AS usuario_consagracaoPretendida, "
					+ "usuario.`consagracaoAtual` AS usuario_consagracaoAtual " + "FROM "
					+ "`usuario` usuario INNER JOIN `endereco` endereco ON usuario.`cpf` = endereco.`endereco_usuario` "
					+ "INNER JOIN `familia` familia ON usuario.`cpf` = familia.`familia_usuario` "
					+ "WHERE usuario.cpf = "+dados.getCpf();

			JRDesignQuery newQuery = new JRDesignQuery();
			newQuery.setText(sql);
			jd.setQuery(newQuery);
			JasperReport jr = JasperCompileManager.compileReport(jd);
			
			InputStream is = new ByteArrayInputStream(dados.getImagem());    
			
			Map<String, Object> parametro = new HashMap<String, Object>();
			parametro.put("IMAGEM", is);
			
			JasperPrint impressao = JasperFillManager.fillReport(jr, parametro, con);
			JasperViewer viewer = new JasperViewer(impressao , false);
			viewer.setVisible(true);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public void fecharCon() {

		try {
			con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

}
