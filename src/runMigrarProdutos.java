import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.Cliente;
import domain.EcfPreVendaCabecalho;
import domain.EcfPreVendaDetalhe;
import domain.Empresa;
import domain.Fornecedor;
import domain.MovimentoEntCabecalho;
import domain.MovimentoEntDetalhe;
import domain.NaturezaOperacao;
import domain.Produto;


public class runMigrarProdutos {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
	
	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();
		
		List<String> produtos = new ArrayList<>();
		
			//
//		produtos.add("13534");
//		produtos.add("3783");
//		produtos.add("3389");
//		produtos.add("3033");
//		produtos.add("3398");
//		produtos.add("14814");
//		produtos.add("1786");
//		produtos.add("1486");
//		produtos.add("1001");
//		produtos.add("14062");
//		produtos.add("1237");
//		produtos.add("14063");
//		produtos.add("79");
//		produtos.add("1979");
//		produtos.add("886");
//		produtos.add("13531");
//		produtos.add("14812");
//		produtos.add("14813");
//		produtos.add("15907");
			
			
		
		for (String produto : produtos) {
			System.out.println(produto); 
			Query q = em.createQuery("select p from Produto p where p.id=:produto",Produto.class);
			q.setParameter("produto", Integer.parseInt(produto));
			
			em.getTransaction().begin();
			for (Produto p : (List<Produto>)q.getResultList()) {
				
				Fornecedor fornecedor = findbyCnpjEmp(p.getEmpresaId().getCnpj(),p.getEmpresaId().getId());
				
				Query qMesclar = em.createQuery("select p from Produto p where p.nome LIKE:pNome and p.id !=:pCod and p.empresaId=:empresa", Produto.class);
				qMesclar.setParameter("pNome", p.getNome());
				qMesclar.setParameter("pCod", p.getId());
				qMesclar.setParameter("empresa", new Empresa(5));
				
				Produto p2 = new Produto();
				
				if(qMesclar.getResultList().size() == 1){
					p2 = (Produto)qMesclar.getSingleResult();
					p2.setQtdEstoque(p2.getQtdEstoque()+p.getQtdEstoque());
					p2.setQtdEstoqueDeposito(p2.getQtdEstoqueDeposito()+p.getQtdEstoqueDeposito());
					em.merge(p2);
				}else{			
					p2.setQtd(p.getQtd());
					p2.setFornecedorId(p.getFornecedorId());
					p2.setGrupoId(p.getGrupoId());
					p2.setMarcasId(p.getMarcasId());
					p2.setUnidadeProduto(p.getUnidadeProduto());
					p2.setgTin(p.getgTin());
					p2.setNome(p.getNome());
					p2.setValorCusto(p.getValorCusto());
					p2.setValorVenda(p.getValorVenda());
					p2.setLucro(p.getLucro());
					p2.setGarantia(p.getGarantia());
					p2.setQtdEstoque(p.getQtdEstoque());
					p2.setEstoqueMin(p.getEstoqueMin());
					p2.setIat(p.getIat());
					p2.setIppt(p.getIppt());
					p2.setNcm(p.getNcm());
					p2.setDataEstoque(p.getDataEstoque());
					p2.setDataAlteracao(new Date());
					p2.setReferencia(p.getReferencia());
					p2.setTipo_item(p.getTipo_item());
					p2.setPeso(p.getPeso());
					p2.setUltima_compra(p.getUltima_compra());
					p2.setCst_forma_tributo(p.getCst_forma_tributo());
					p2.setSimples_nacional(p.getSimples_nacional());
					p2.setEcf_cup_filcal(p.getEcf_cup_filcal());
					p2.setOutras_tb_desc(p.getOutras_tb_desc());
					p2.setAval_cliente(p.getAval_cliente());
					p2.setTaxaIpi(p.getTaxaIpi());
					p2.setFrete(p.getFrete());
					p2.setMargemLucro(p.getMargemLucro());
					p2.setFracionar(p.getFracionar());
					p2.setUtilizaSeriais(p.getUtilizaSeriais());
					p2.setStatus(p.getStatus());
					p2.setCst_origem(p.getCst_origem());
					p2.setDiferenca_aliquota(p.getDiferenca_aliquota());
					p2.setPermitir_desconto(p.getPermitir_desconto());
					p2.setCusto_total(p.getCusto_total());
					p2.setTaxaIcms(p.getTaxaIcms());
					p2.setCfop(p.getCfop());
					p2.setCest(p.getCest());
					p2.setNome_produto_loja(p.getNome_produto_loja());
					p2.setPreco_promocional(p.getPreco_promocional());
					p2.setProduto_ativado(p.getProduto_ativado());
					p2.setProduto_destaque(p.getProduto_destaque());
					p2.setUrl_video_youtube(p.getUrl_video_youtube());
					p2.setDesc_produto(p.getDesc_produto());
					p2.setSic_loja(p.isSic_loja());
					p2.setCod_pro_loja(p.getCod_pro_loja());
					p2.setQtdEstoqueDeposito(p.getQtdEstoqueDeposito());
					p2.setAjustado(p.getAjustado());			
					p2.setEmpresaId(new Empresa(5));			
					em.persist(p2);			
				}
				
				if(p2 != null){
					
					//Gerar Histórico de Compra de P2
					Integer codCompra = Integer.parseInt(getNextIdCompra());
					MovimentoEntCabecalho compra = new MovimentoEntCabecalho();
					//compra.setId(Integer.parseInt(getNextIdCompra()));
					compra.setEmpresa_id(p2.getEmpresaId().getId());
					compra.setCodNf(0);
					compra.setFornecedor(fornecedor.getId());
					compra.setQtdItens(new Float(0));
					compra.setValorTotal(new Float(0));
					compra.setDataHora(new Date());
					compra.setUsuario("scripts");
					compra.setSituacao("F");
					compra.setTipo("MIGRACAO");
					em.persist(compra);
					
					MovimentoEntDetalhe compraDetalhe = new MovimentoEntDetalhe();
					compraDetalhe.setMovimentoEntCabecalhoId(codCompra);
					compraDetalhe.setProdutoId(p2.getId());
					compraDetalhe.setQuantidade(p.getQtdEstoque()+p.getQtdEstoqueDeposito());
					compraDetalhe.setValorCusto(p2.getValorCusto()); //<- Acrescentado Valor de Custo na Entrada
					em.persist(compraDetalhe);					
				}
				
				//Gerar Histórico de Saída de P
				NaturezaOperacao natureza = naturezaFindbyName("VENDA", p.getEmpresaId().getId());
				
				Integer codVenda = Integer.parseInt(getNextIdVenda());
				EcfPreVendaCabecalho pedido = new EcfPreVendaCabecalho();
				//pedido.setId(Integer.parseInt(getNextIdVenda()));
				
				//alterar para espaço -> 5
				pedido.setCliente(findByCpf("33056081000109", 1));
				pedido.setEmpresa_id(p.getEmpresaId().getId());
				pedido.setData(new Date());
				pedido.setTipo("MIGRACAO");
				pedido.setSituacao("F");			
				pedido.setNaturezaOperacao(natureza.getId());
				em.persist(pedido);
				
				EcfPreVendaDetalhe detalhe = new EcfPreVendaDetalhe();
				detalhe.setEcfPreVendaCabecalhoId(codVenda);
				detalhe.setProdutoId(p.getId());
				detalhe.setQuantidade(p.getQtdEstoque()+p.getQtdEstoqueDeposito());
				em.persist(detalhe); 
								
				//Zerar qtd
				p.setQtdEstoque(new Float(0));
				p.setQtdEstoqueDeposito(new Float(0));
				//limpar loja integrada
				p.setNome_produto_loja(null);
				p.setPreco_promocional(new Float(0));
				p.setProduto_ativado(null);
				p.setProduto_destaque(null);
				p.setUrl_video_youtube(null);
				p.setDesc_produto(null);
				p.setSic_loja(false);
				p.setCod_pro_loja(null);
				
				em.merge(p);
				
			}
			em.getTransaction().commit();
		}
		
	}
	public static Produto findProduto(String nome, Empresa e){
		
		EntityManager em = emf.createEntityManager();
		
		Query q = em.createQuery("select p from Produto p where p.nome like:n and p.empresaId=:e", Produto.class);
		q.setParameter("n", nome);
		q.setParameter("e", e);
		
		if(q.getResultList().size() == 1){
			return (Produto)q.getSingleResult();
		}
		
		return null;
	}
	public static String getNextIdVenda(){
		
		EntityManager em = emf.createEntityManager();
		try{
			Query q = em.createNativeQuery("SHOW TABLE STATUS LIKE 'ecf_pre_venda_cabecalho'");	
			Object result = q.getSingleResult();
			Object[] ob = (Object[]) result;
			String cod = ob[10].toString();
			
			return cod;
		}catch (Exception e){
			if(em.getTransaction().isActive()){
			   em.getTransaction().rollback();
			}
			e.printStackTrace();
			System.out.println("Erro ao tentar pegar o próximo ID da venda: "+e.getMessage());
			return null;
		}
	}
	
	public static String getNextIdCompra(){
		
		EntityManager em = emf.createEntityManager();
		try{
			Query q = em.createNativeQuery("SHOW TABLE STATUS LIKE 'movimento_ent_cabecalho'");	
			Object result = q.getSingleResult();
			Object[] ob = (Object[]) result;
			String cod = ob[10].toString();
			
			return cod;
		}catch (Exception e){
			if(em.getTransaction().isActive()){
			   em.getTransaction().rollback();
			}
			e.printStackTrace();
			System.out.println("Erro ao tentar pegar o próximo ID do compra: "+e.getMessage());
			return null;
		}
	}
	public static Fornecedor findbyCnpjEmp(String cnpj, Integer empresa){
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("select f from Fornecedor f where f.cnpj =:cnpj and f.empresa=:empresa", Fornecedor.class);
		q.setParameter("cnpj", cnpj);
		q.setParameter("empresa", new Empresa(empresa)); 
		
		if(q.getResultList().size() > 0){
			return (Fornecedor)q.getResultList().get(0);
		}
		
		return null;
	}
	public static NaturezaOperacao naturezaFindbyName(String desc, Integer empresa){
		EntityManager em = emf.createEntityManager();
		
		Query q = em.createQuery("select c from NaturezaOperacao c where c.empresa_id=:empresa and c.descricao = :desc and c.status='ATIVO'", NaturezaOperacao.class);
		q.setParameter("empresa", empresa);
		q.setParameter("desc", desc);
		
		if(q.getResultList().size() == 1){
			return (NaturezaOperacao)q.getSingleResult();
		}
		
		return null;
	}
	
	public static Cliente findByCpf(String cpf, Integer empresa){
		EntityManager em = emf.createEntityManager();
		
		try{
			Query q = em.createQuery("select c from Cliente c where c.empresa =:empresa and c.doc_cpf_cnpj =:doc_cpf_cnpj", Cliente.class);
			q.setParameter("empresa", empresa);
			q.setParameter("doc_cpf_cnpj", cpf);
			Cliente cliente =null;		
			
			if(q.getResultList().size() >0){
				cliente = (Cliente)q.getSingleResult();
			}
			
			return cliente;
		
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
