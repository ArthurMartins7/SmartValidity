package model.seletor;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ItemProdutoSeletor extends BaseSeletor {

	private LocalDate dataInicioFabricacao;
	private LocalDate dataFinalFabricacao;
	private LocalDate dataInicioVencimento;
	private LocalDate dataFinalVencimento;
	private LocalDateTime dataInicioRecebimento;
	private LocalDateTime dataFinalRecebimento;
	private String lote;
	private String nomeProduto;
	private String marca;
	private String codigoBarras;
	private String nomeCorredor;
	private String nomeCategoria;

	public ItemProdutoSeletor() {

	}

	public LocalDate getDataInicioFabricacao() {
		return dataInicioFabricacao;
	}

	public void setDataInicioFabricacao(LocalDate dataInicioFabricacao) {
		this.dataInicioFabricacao = dataInicioFabricacao;
	}

	public LocalDate getDataFinalFabricacao() {
		return dataFinalFabricacao;
	}

	public void setDataFinalFabricacao(LocalDate dataFinalFabricacao) {
		this.dataFinalFabricacao = dataFinalFabricacao;
	}

	public LocalDate getDataInicioVencimento() {
		return dataInicioVencimento;
	}

	public void setDataInicioVencimento(LocalDate dataInicioVencimento) {
		this.dataInicioVencimento = dataInicioVencimento;
	}

	public LocalDate getDataFinalVencimento() {
		return dataFinalVencimento;
	}

	public void setDataFinalVencimento(LocalDate dataFinalVencimento) {
		this.dataFinalVencimento = dataFinalVencimento;
	}

	public LocalDateTime getDataInicioRecebimento() {
		return dataInicioRecebimento;
	}

	public void setDataInicioRecebimento(LocalDateTime dataInicioRecebimento) {
		this.dataInicioRecebimento = dataInicioRecebimento;
	}

	public LocalDateTime getDataFinalRecebimento() {
		return dataFinalRecebimento;
	}

	public void setDataFinalRecebimento(LocalDateTime dataFinalRecebimento) {
		this.dataFinalRecebimento = dataFinalRecebimento;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getNomeCorredor() {
		return nomeCorredor;
	}

	public void setNomeCorredor(String nomeCorredor) {
		this.nomeCorredor = nomeCorredor;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	
	

}
