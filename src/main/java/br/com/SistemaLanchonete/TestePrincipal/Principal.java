package br.com.SistemaLanchonete.TestePrincipal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.SistemaLanchonete.Domain.BairroBean;
import br.com.SistemaLanchonete.Domain.ClienteBean;
import br.com.SistemaLanchonete.Domain.EnderecoBean;
import br.com.SistemaLanchonete.Domain.EnderecoPessoaBean;
import br.com.SistemaLanchonete.Domain.EstadoBean;
import br.com.SistemaLanchonete.Domain.FormaPagamentoBean;
import br.com.SistemaLanchonete.Domain.FuncionarioBean;
import br.com.SistemaLanchonete.Domain.ItemPedidoAdicionalBean;
import br.com.SistemaLanchonete.Domain.ItemPedidoBean;
import br.com.SistemaLanchonete.Domain.LogradouroBean;
import br.com.SistemaLanchonete.Domain.MunicipioBean;
import br.com.SistemaLanchonete.Domain.PedidoBean;
import br.com.SistemaLanchonete.Domain.PrecoProdutoBean;
import br.com.SistemaLanchonete.Domain.ProdutoAdicionalBean;
import br.com.SistemaLanchonete.Domain.ProdutoBean;
import br.com.SistemaLanchonete.Domain.ProdutoCategoriaBean;
import br.com.SistemaLanchonete.Repository.GenericDAO;


public class Principal {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		// ESTADO
		{
			GenericDAO<EstadoBean> dao = new GenericDAO<EstadoBean>();			
			ArrayList<EstadoBean> lista 	= new ArrayList<EstadoBean>();
			try {
				lista = dao.findLike(EstadoBean.class, new EstadoBean(0, "", ""));//
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ERRO FIND LIKE !!!!!!!!!!!!!!! = " + e.getMessage());
			}
			
			for (EstadoBean bean : lista) {
				try {
					dao.remove(EstadoBean.class, bean.getCdEstado());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO REMOVE !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
			lista = new ArrayList<EstadoBean>();
			lista.add(new EstadoBean(0, "Santa Catarina", "SC"));
			lista.add(new EstadoBean(0, "Acre", "AC"));
			lista.add(new EstadoBean(0, "Calif�rnia", "CA"));
			System.out.println(lista);
			for (EstadoBean bean : lista) {
				try {
					System.out.println(bean);
					dao.save(bean, 0);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO INSER��O !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
			
			for (int i = 1; i <= 3 ; ++i) {
				try {
					EstadoBean bean = dao.findById(EstadoBean.class, i);
					bean.setDsEstado(bean.getDsEstado() + " - Altera��o");
					dao.save(bean, bean.getCdEstado());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO BUSCA E ALTERA��O !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
			
			for (int i = 1; i <= 3 ; ++i) {
				try {
					EstadoBean bean = dao.findById(EstadoBean.class, i);
					System.out.println("ID: " + bean.getCdEstado() + ". DS: " + bean.getDsEstado());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO BUSCA !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
		}
		
		// MUNIC�PIO
		{
			GenericDAO<MunicipioBean> dao = new GenericDAO<MunicipioBean>();
			ArrayList<MunicipioBean> lista 	= null;
			try {
				lista = dao.findLike(MunicipioBean.class, new MunicipioBean(0, null, ""));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ERRO FIND LIKE !!!!!!!!!!!!!!! = " + e.getMessage());
			}
			
			for (MunicipioBean bean : lista) {
				try {
					dao.remove(MunicipioBean.class, bean.getCdMunicipio());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO REMOVE !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
			lista.clear();
			
			GenericDAO<EstadoBean> filho = new GenericDAO<EstadoBean>();
			ArrayList<EstadoBean> listaFilho 	= null;
			try {
				listaFilho = filho.findLike(EstadoBean.class, new EstadoBean(0, "", ""));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ERRO FIND LIKE !!!!!!!!!!!!!!! = " + e.getMessage());
			}
			
			lista.add(new MunicipioBean(0, listaFilho.get(0), "Blumenau"));
			lista.add(new MunicipioBean(0, listaFilho.get(1), "Fenda do Bikini"));
			lista.add(new MunicipioBean(0, listaFilho.get(2), "This is the City"));
		
			for (MunicipioBean bean : lista) {
				try {
					dao.save(bean, bean.getCdMunicipio());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO INSER��O !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
		
			for (int i = 1; i <= 3 ; ++i) {
				try {
					MunicipioBean bean = dao.findById(MunicipioBean.class, i);
					bean.setDsMunicipio(bean.getDsMunicipio() + " - Altera��o");
					dao.save(bean, bean.getCdMunicipio());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO BUSCA E ALTERA��O !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
		
			for (int i = 1; i <= 3 ; ++i) {
				try {
					MunicipioBean bean = dao.findById(MunicipioBean.class, i);
					System.out.println("ID: " + bean.getCdMunicipio() + ". DS: " + bean.getDsMunicipio());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO BUSCA !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
		}
		
		// BAIRRO
		{
			GenericDAO<BairroBean> dao = new GenericDAO<BairroBean>();
			ArrayList<BairroBean> lista 	= null;
			try {
				lista = dao.findLike(BairroBean.class, new BairroBean(0, null, ""));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ERRO FIND LIKE !!!!!!!!!!!!!!! = " + e.getMessage());
			}
			
			for (BairroBean bean : lista) {
				try {
					dao.remove(BairroBean.class, bean.getCdBairro());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO REMOVE !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
			lista.clear();
			
			GenericDAO<MunicipioBean> filho = new GenericDAO<MunicipioBean>();
			ArrayList<MunicipioBean> listaFilho 	= null;
			try {
				listaFilho = filho.findLike(MunicipioBean.class, new MunicipioBean(0, null, ""));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ERRO FIND LIKE !!!!!!!!!!!!!!! = " + e.getMessage());
			}
			
			lista.add(new BairroBean(0, listaFilho.get(0), "Centro"));
			lista.add(new BairroBean(0, listaFilho.get(1), "Escola da Professora Puff"));
			lista.add(new BairroBean(0, listaFilho.get(2), "Neighborhood"));
		
			for (BairroBean bean : lista) {
				try {
					dao.save(bean, bean.getCdBairro());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO INSER��O !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
		
			for (int i = 1; i <= 3 ; ++i) {
				try {
					BairroBean bean = dao.findById(BairroBean.class, i);
					bean.setDsBairro(bean.getDsBairro() + " - Altera��o");
					dao.save(bean, bean.getCdBairro());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO BUSCA E ALTERA��O !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
		
			for (int i = 1; i <= 3 ; ++i) {
				try {
					BairroBean bean = dao.findById(BairroBean.class, i);
					System.out.println("ID: " + bean.getCdBairro() + ". DS: " + bean.getDsBairro());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO BUSCA !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
		}
		
		// LOGRADOURO
		{
			GenericDAO<LogradouroBean> dao = new GenericDAO<LogradouroBean>();
			ArrayList<LogradouroBean> lista 	= null;
			try {
				lista = dao.findLike(LogradouroBean.class, new LogradouroBean(0, null, 0, ""));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ERRO FIND LIKE !!!!!!!!!!!!!!! = " + e.getMessage());
			}
			
			for (LogradouroBean bean : lista) {
				try {
					dao.remove(LogradouroBean.class, bean.getCdLogradouro());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO REMOVE !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
			lista.clear();
			
			GenericDAO<BairroBean> filho = new GenericDAO<BairroBean>();
			ArrayList<BairroBean> listaFilho 	= null;
			try {
				listaFilho = filho.findLike(BairroBean.class, new BairroBean(0, null, ""));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ERRO FIND LIKE !!!!!!!!!!!!!!! = " + e.getMessage());
			}
			
			lista.add(new LogradouroBean(0, listaFilho.get(0), 11111111, "Rua Tal"));
			lista.add(new LogradouroBean(0, listaFilho.get(1), 22222222, "Rua do Seu Sirigueijo"));
			lista.add(new LogradouroBean(0, listaFilho.get(2), 33333333,"Street"));
		
			for (LogradouroBean bean : lista) {
				try {
					dao.save(bean, bean.getCdLogradouro());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO INSER��O !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
		
			for (int i = 1; i <= 3 ; ++i) {
				try {
					LogradouroBean bean = dao.findById(LogradouroBean.class, i);
					bean.setDsLogradouro(bean.getDsLogradouro() + " - Altera��o");
					dao.save(bean, bean.getCdLogradouro());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO BUSCA E ALTERA��O !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
		
			for (int i = 1; i <= 3 ; ++i) {
				try {
					LogradouroBean bean = dao.findById(LogradouroBean.class, i);
					System.out.println("ID: " + bean.getCdLogradouro() + ". DS: " + bean.getDsLogradouro());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO BUSCA !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
		}
		
		// ENDERE�O
		{
			GenericDAO<EnderecoBean> dao = new GenericDAO<EnderecoBean>();
			ArrayList<EnderecoBean> lista 	= null;
			try {
				lista = dao.findLike(EnderecoBean.class, new EnderecoBean(0, null, 0, "", ""));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ERRO FIND LIKE !!!!!!!!!!!!!!! = " + e.getMessage());
			}
			
			for (EnderecoBean bean : lista) {
				try {
					dao.remove(EnderecoBean.class, bean.getCdEndereco());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO REMOVE !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
			lista.clear();
			
			GenericDAO<LogradouroBean> filho = new GenericDAO<LogradouroBean>();
			ArrayList<LogradouroBean> listaFilho 	= null;
			try {
				listaFilho = filho.findLike(LogradouroBean.class, new LogradouroBean(0, null, 0, ""));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ERRO FIND LIKE !!!!!!!!!!!!!!! = " + e.getMessage());
			}
			
			lista.add(new EnderecoBean(0, listaFilho.get(0), 1, "Casa do Yago", "Minha casa"));
			lista.add(new EnderecoBean(0, listaFilho.get(1), 2, "Casa do Bob Esponja", "Ao lado do Lula Molusco"));
			lista.add(new EnderecoBean(0, listaFilho.get(2), 3, "House", "Dr. House"));
		
			for (EnderecoBean bean : lista) {
				try {
					dao.save(bean, bean.getCdEndereco());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO INSER��O !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
		
			for (int i = 1; i <= 3 ; ++i) {
				try {
					EnderecoBean bean = dao.findById(EnderecoBean.class, i);
					bean.setDsComplemento(bean.getDsComplemento() + " - Altera��o");
					dao.save(bean, bean.getCdEndereco());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO BUSCA E ALTERA��O !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
		
			for (int i = 1; i <= 3 ; ++i) {
				try {
					EnderecoBean bean = dao.findById(EnderecoBean.class, i);
					System.out.println("ID: " + bean.getCdEndereco() + ". DS: " + bean.getDsComplemento());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO BUSCA !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
		}
		
		// FUNCION�RIO
		{
			GenericDAO<FuncionarioBean> dao = new GenericDAO<FuncionarioBean>();			
			ArrayList<FuncionarioBean> lista 	= new ArrayList<FuncionarioBean>();
			try {
				lista = dao.findLike(FuncionarioBean.class, new FuncionarioBean(0, "", "", "", null, false, 0, "", null, "", 0));//
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ERRO FIND LIKE !!!!!!!!!!!!!!! = " + e.getMessage());
			}
			
			for (FuncionarioBean bean : lista) {
				try {					
					dao.remove(FuncionarioBean.class, bean.getCdFuncionario());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO REMOVE !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
			
			lista = new ArrayList<FuncionarioBean>();
			
			GenericDAO<EnderecoBean> filho = new GenericDAO<EnderecoBean>();
			ArrayList<EnderecoPessoaBean> listaEndPessoa = new ArrayList<EnderecoPessoaBean>();
			listaEndPessoa.add(new EnderecoPessoaBean(null, null, new Date(), true));
			listaEndPessoa.add(new EnderecoPessoaBean(null, null, new Date(), true));
			listaEndPessoa.add(new EnderecoPessoaBean(null, null, new Date(), true));
			
			try {
				ArrayList<EnderecoBean> listaFilho 	= filho.findLike(EnderecoBean.class, new EnderecoBean(0, null, 0, "", ""));
				listaEndPessoa.get(0).setEndereco(listaFilho.get(0));
				listaEndPessoa.get(1).setEndereco(listaFilho.get(1));
				listaEndPessoa.get(2).setEndereco(listaFilho.get(2));
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ERRO FIND LIKE !!!!!!!!!!!!!!! = " + e.getMessage());
			}
			
			lista.add(new FuncionarioBean(0, "Fulano de Tal", "11111111", "22222222", new Date(), true, 0, "Presidente", "ADM", "ADM", 1));
			lista.add(new FuncionarioBean(0, "Lula Molusco", "33333333", "44444444",  new Date(), true, 0, "Cheff", "Lula", "123", 2));
			lista.add(new FuncionarioBean(0, "David Hasselhoff", "55555555", "66666666",  new Date(), true, 0, "Entregador", "Login", "Senha", 3));
			lista.get(0).addEnderecoPessoa(listaEndPessoa.get(0));
			lista.get(1).addEnderecoPessoa(listaEndPessoa.get(1));
			lista.get(2).addEnderecoPessoa(listaEndPessoa.get(2));
			System.out.println(lista);
			for (FuncionarioBean bean : lista) {
				try {
					System.out.println(bean);
					dao.save(bean, 0);
					
					GenericDAO<EnderecoPessoaBean> epDao = new GenericDAO<EnderecoPessoaBean>();
					List<EnderecoPessoaBean> daoEndereco = bean.getEnderecoPessoas();
					
					for (EnderecoPessoaBean enderecoPessoaBean : daoEndereco) {
						enderecoPessoaBean.setPessoa(bean);
						epDao.save(enderecoPessoaBean, 0);
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO INSER��O !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
			
			for (int i = 1; i <= 3 ; ++i) {
				try {
					FuncionarioBean bean = dao.findById(FuncionarioBean.class, i);
					bean.setDsNome(bean.getDsNome() + " - Altera��o");
					dao.save(bean, bean.getCdFuncionario());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO BUSCA E ALTERA��O !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
			
			for (int i = 1; i <= 3 ; ++i) {
				try {
					FuncionarioBean bean = dao.findById(FuncionarioBean.class, i);
					System.out.println("ID: " + bean.getCdFuncionario() + ". DS: " + bean.getDsNome());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO BUSCA !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
		}
		
		// TESTE CLIENTE
		{
			GenericDAO<ClienteBean> dao = new GenericDAO<ClienteBean>();
			ArrayList<ClienteBean> lista 	= null;
			try {
				lista = dao.findLike(ClienteBean.class, new ClienteBean(0, "", "", "", new Date(), false, 0, ""));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ERRO FIND LIKE !!!!!!!!!!!!!!! = " + e.getMessage());
			}
			
			for (ClienteBean bean : lista) {
				try {
					dao.remove(ClienteBean.class, bean.getCdCliente());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO REMOVE !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
			lista.clear();
			
			GenericDAO<EnderecoBean> filho = new GenericDAO<EnderecoBean>();
			ArrayList<EnderecoPessoaBean> listaEndPessoa = new ArrayList<EnderecoPessoaBean>();
			listaEndPessoa.add(new EnderecoPessoaBean(null, null, new Date(), true));
			listaEndPessoa.add(new EnderecoPessoaBean(null, null, new Date(), true));
			listaEndPessoa.add(new EnderecoPessoaBean(null, null, new Date(), true));
			
			try {
				ArrayList<EnderecoBean> listaFilho 	= filho.findLike(EnderecoBean.class, new EnderecoBean(0, null, 0, "", ""));
				listaEndPessoa.get(0).setEndereco(listaFilho.get(0));
				listaEndPessoa.get(1).setEndereco(listaFilho.get(1));
				listaEndPessoa.get(2).setEndereco(listaFilho.get(2));
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ERRO FIND LIKE !!!!!!!!!!!!!!! = " + e.getMessage());
			}
			
			lista.add(new ClienteBean(0, "Ciclano", "11111111", "11111111", new Date(), true, 0, "Come muito"));
			lista.add(new ClienteBean(0, "Sandy", "11111111", "11111111", new Date(), true, 0, "Esquilo"));
			lista.add(new ClienteBean(0, "Arnold Schwarzenegger", "11111111", "11111111", new Date(), true, 0, "I'll be back"));
			lista.get(0).addEnderecoPessoa(listaEndPessoa.get(0));
			lista.get(1).addEnderecoPessoa(listaEndPessoa.get(1));
			lista.get(2).addEnderecoPessoa(listaEndPessoa.get(2));
			
			for (ClienteBean bean : lista) {
				try {
					dao.save(bean, bean.getCdCliente());
					
					GenericDAO<EnderecoPessoaBean> epDao = new GenericDAO<EnderecoPessoaBean>();
					List<EnderecoPessoaBean> daoEndereco = bean.getEnderecoPessoas();
					
					for (EnderecoPessoaBean enderecoPessoaBean : daoEndereco) {
						enderecoPessoaBean.setPessoa(bean);
						epDao.save(enderecoPessoaBean, 0);
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO INSER��O !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
		
			for (int i = 4; i <= 6 ; ++i) {
				try {
					ClienteBean bean = dao.findById(ClienteBean.class, i);
					bean.setDsNome(bean.getDsNome() + " - Altera��o");
					dao.save(bean, bean.getCdCliente());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO BUSCA E ALTERA��O !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
		
			for (int i = 4; i <= 6 ; ++i) {
				try {
					ClienteBean bean = dao.findById(ClienteBean.class, i);
					System.out.println("ID: " + bean.getCdCliente() + ". DS: " + bean.getDsNome());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO BUSCA !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
		}
		
		// TESTE CATEGORIA
		{
			GenericDAO<ProdutoCategoriaBean> dao = new GenericDAO<ProdutoCategoriaBean>();			
			ArrayList<ProdutoCategoriaBean> lista 	= new ArrayList<ProdutoCategoriaBean>();
			try {
				lista = dao.findLike(ProdutoCategoriaBean.class, new ProdutoCategoriaBean(0, ""));//
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ERRO FIND LIKE !!!!!!!!!!!!!!! = " + e.getMessage());
			}
			
			for (ProdutoCategoriaBean bean : lista) {
				try {
					dao.remove(ProdutoCategoriaBean.class, bean.getCdProdutoCategoria());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO REMOVE !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
			lista = new ArrayList<ProdutoCategoriaBean>();
			lista.add(new ProdutoCategoriaBean(0, "Lanche"));
			lista.add(new ProdutoCategoriaBean(0, "Bebida"));
			lista.add(new ProdutoCategoriaBean(0, "Sobremesa"));
			System.out.println(lista);
			for (ProdutoCategoriaBean bean : lista) {
				try {
					System.out.println(bean);
					dao.save(bean, 0);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO INSER��O !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
			
			for (int i = 1; i <= 3 ; ++i) {
				try {
					ProdutoCategoriaBean bean = dao.findById(ProdutoCategoriaBean.class, i);
					bean.setDsCategoria(bean.getDsCategoria() + " - Altera��o");
					dao.save(bean, bean.getCdProdutoCategoria());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO BUSCA E ALTERA��O !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
			
			for (int i = 1; i <= 3 ; ++i) {
				try {
					ProdutoCategoriaBean bean = dao.findById(ProdutoCategoriaBean.class, i);
					System.out.println("ID: " + bean.getCdProdutoCategoria() + ". DS: " + bean.getDsCategoria());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO BUSCA !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
		}
		
		// TESTE FORMA DE PAGAMENTO
		{
			GenericDAO<FormaPagamentoBean> dao = new GenericDAO<FormaPagamentoBean>();
			ArrayList<FormaPagamentoBean> lista 	= null;
			try {
				lista = dao.findLike(FormaPagamentoBean.class, new FormaPagamentoBean(0, ""));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ERRO FIND LIKE !!!!!!!!!!!!!!! = " + e.getMessage());
			}
			for (FormaPagamentoBean bean : lista) {
				try {
					dao.remove(FormaPagamentoBean.class, bean.getCdFormaPagamento());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO REMOVE !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
			lista.clear();
			lista.add(new FormaPagamentoBean(0, "Cart�o"));
			lista.add(new FormaPagamentoBean(0, "Boleto"));
			lista.add(new FormaPagamentoBean(0, "Dinheiro"));
		
			for (FormaPagamentoBean bean : lista) {
				try {
					dao.save(bean, bean.getCdFormaPagamento());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO INSER��O !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
		
			for (int i = 1; i <= 3 ; ++i) {
				try {
					FormaPagamentoBean bean = dao.findById(FormaPagamentoBean.class, i);
					bean.setDsFormaPagamento(bean.getDsFormaPagamento() + " - Altera��o");
					dao.save(bean, bean.getCdFormaPagamento());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO BUSCA E ALTERA��O !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
		
			for (int i = 1; i <= 3 ; ++i) {
				try {
					FormaPagamentoBean bean = dao.findById(FormaPagamentoBean.class, i);
					System.out.println("ID: " + bean.getCdFormaPagamento() + ". DS: " + bean.getDsFormaPagamento());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO BUSCA !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
		}
		
		// TESTE PRODUTO		
		{
			GenericDAO<ProdutoBean> dao = new GenericDAO<ProdutoBean>();
			ArrayList<ProdutoBean> lista 	= null;
			try {
				lista = dao.findLike(ProdutoBean.class, new ProdutoBean(0, null, "", "", false));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ERRO FIND LIKE !!!!!!!!!!!!!!! = " + e.getMessage());
			}
			
			for (ProdutoBean bean : lista) {
				try {
					dao.remove(ProdutoBean.class, bean.getCdProduto());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO REMOVE !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
			lista.clear();
			
			GenericDAO<ProdutoCategoriaBean> filho = new GenericDAO<ProdutoCategoriaBean>();
			ArrayList<ProdutoCategoriaBean> listaFilho 	= null;
			try {
				listaFilho = filho.findLike(ProdutoCategoriaBean.class, new ProdutoCategoriaBean(0, ""));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ERRO FIND LIKE !!!!!!!!!!!!!!! = " + e.getMessage());
			}
			
			lista.add(new ProdutoBean(0, listaFilho.get(0), "#001", "X-Burguer", true));
			lista.add(new ProdutoBean(0, listaFilho.get(1), "#002", "Coca-Cola", true));
			lista.add(new ProdutoBean(0, listaFilho.get(2), "#003", "A�a�", true));
		
			for (ProdutoBean bean : lista) {
				try {
					dao.save(bean, bean.getCdProduto());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO INSER��O !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
		
			for (int i = 1; i <= 3 ; ++i) {
				try {
					ProdutoBean bean = dao.findById(ProdutoBean.class, i);
					bean.setDsProduto(bean.getDsProduto() + " - Altera��o");
					dao.save(bean, bean.getCdProduto());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO BUSCA E ALTERA��O !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
		
			for (int i = 1; i <= 3 ; ++i) {
				try {
					ProdutoBean bean = dao.findById(ProdutoBean.class, i);
					System.out.println("ID: " + bean.getCdProduto() + ". DS: " + bean.getDsProduto());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO BUSCA !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
		}
		
		// PRE�O PRODUTO
		{
			GenericDAO<PrecoProdutoBean> dao = new GenericDAO<PrecoProdutoBean>();
			ArrayList<PrecoProdutoBean> lista 	= null;
			try {
				lista = dao.findLike(PrecoProdutoBean.class, new PrecoProdutoBean(0, null, 0, null, null, false));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ERRO FIND LIKE !!!!!!!!!!!!!!! = " + e.getMessage());
			}
			
			for (PrecoProdutoBean bean : lista) {
				try {
					dao.remove(PrecoProdutoBean.class, bean.getCdPrecoProduto());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO REMOVE !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
			lista.clear();
			
			GenericDAO<ProdutoBean> filho = new GenericDAO<ProdutoBean>();
			ArrayList<ProdutoBean> listaFilho 	= null;
			try {
				listaFilho = filho.findLike(ProdutoBean.class, new ProdutoBean(0, null, "", "", false));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ERRO FIND LIKE !!!!!!!!!!!!!!! = " + e.getMessage());
			}
			
			lista.add(new PrecoProdutoBean(0, listaFilho.get(0), 19, new Date(2017, 1, 1), new Date(2017, 2, 1), true));
			lista.add(new PrecoProdutoBean(0, listaFilho.get(1), 48, new Date(2017, 1, 1), new Date(2017, 2, 1), true));
			lista.add(new PrecoProdutoBean(0, listaFilho.get(2), 25, new Date(2017, 1, 1), new Date(2017, 2, 1), true));
		
			for (PrecoProdutoBean bean : lista) {
				try {
					dao.save(bean, bean.getCdPrecoProduto());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO INSER��O !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
		
			for (int i = 1; i <= 3 ; ++i) {
				try {
					PrecoProdutoBean bean = dao.findById(PrecoProdutoBean.class, i);
					bean.setVlPrecoAtual((bean.getVlPrecoAtual() + 1000));
					dao.save(bean, bean.getCdPrecoProduto());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO BUSCA E ALTERA��O !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
		
			for (int i = 1; i <= 3 ; ++i) {
				try {
					PrecoProdutoBean bean = dao.findById(PrecoProdutoBean.class, i);
					System.out.println("ID: " + bean.getCdPrecoProduto() + ". PRE�O ATUAL: " + bean.getVlPrecoAtual());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO BUSCA !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
		}
		
		// PRODUTO ADICIONAL BEAN
		{
			GenericDAO<ProdutoAdicionalBean> dao = new GenericDAO<ProdutoAdicionalBean>();			
			ArrayList<ProdutoAdicionalBean> lista 	= null;
			try {
				lista = dao.findLike(ProdutoAdicionalBean.class, new ProdutoAdicionalBean(0, "", 0));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ERRO FIND LIKE !!!!!!!!!!!!!!! = " + e.getMessage());
			}
			
			for (ProdutoAdicionalBean bean : lista) {
				try {
					dao.remove(ProdutoAdicionalBean.class, bean.getCdProdutoAdicional());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO REMOVE !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
			lista.clear();
			lista.add(new ProdutoAdicionalBean(0, "Bacon", 10));
			lista.add(new ProdutoAdicionalBean(0, "Azeitona", 25));
			lista.add(new ProdutoAdicionalBean(0, "Rodelas de Banana", 1));
			
			for (ProdutoAdicionalBean bean : lista) {
				try {
					dao.save(bean, bean.getCdProdutoAdicional());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO INSER��O !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
			
			for (int i = 1; i <= 3 ; ++i) {
				try {
					ProdutoAdicionalBean bean = dao.findById(ProdutoAdicionalBean.class, i);
					bean.setDsAdicional(bean.getDsAdicional() + " - Altera��o");
					dao.save(bean, bean.getCdProdutoAdicional());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO BUSCA E ALTERA��O !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
			
			for (int i = 1; i <= 3 ; ++i) {
				try {
					ProdutoAdicionalBean bean = dao.findById(ProdutoAdicionalBean.class, i);
					System.out.println("ID: " + bean.getCdProdutoAdicional() + ". DS: " + bean.getDsAdicional());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO BUSCA !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
		}
		
		// PEDIDO - ITEM PEDIDO - ITEM PEDIDO ADICIONAL
		{
			GenericDAO<PedidoBean> dao = new GenericDAO<PedidoBean>();			
			ArrayList<PedidoBean> lista 	= new ArrayList<PedidoBean>();
			try {
				lista = dao.findLike(PedidoBean.class, new PedidoBean(0, null, null, null, null, 0, null, 0, 0, 0, 0, null, 0, ""));//
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ERRO FIND LIKE !!!!!!!!!!!!!!! = " + e.getMessage());
			}
			
			for (PedidoBean bean : lista) {
				try {
					dao.remove(PedidoBean.class, bean.getCdPedido());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO REMOVE !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
			lista = new ArrayList<PedidoBean>();
			
			GenericDAO<ClienteBean> filho1 = new GenericDAO<ClienteBean>();
			ArrayList<ClienteBean> listaFilho1 	= null;
			try {
				listaFilho1 = filho1.findLike(ClienteBean.class, new ClienteBean(0, "", "", "", new Date(), false, 0, ""));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ERRO FIND LIKE !!!!!!!!!!!!!!! = " + e.getMessage());
			}
			
			GenericDAO<FuncionarioBean> filho2 = new GenericDAO<FuncionarioBean>();
			ArrayList<FuncionarioBean> listaFilho2 	= null;
			try {
				listaFilho2 = filho2.findLike(FuncionarioBean.class, new FuncionarioBean(0, "", "", "", null, false, 0, "", null, "", 0));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ERRO FIND LIKE !!!!!!!!!!!!!!! = " + e.getMessage());
			}
			
			GenericDAO<FormaPagamentoBean> filho3 = new GenericDAO<FormaPagamentoBean>();
			ArrayList<FormaPagamentoBean> listaFilho3 	= null;
			try {
				listaFilho3 = filho3.findLike(FormaPagamentoBean.class, new FormaPagamentoBean(0, ""));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ERRO FIND LIKE !!!!!!!!!!!!!!! = " + e.getMessage());
			}
			
			lista.add(new PedidoBean(0, listaFilho1.get(0), listaFilho2.get(0), listaFilho2.get(1), listaFilho3.get(0)
					, 1, new Date(), 40, 55, 80, 5, new Date(), 25, "Cr�tico"));
			
			lista.add(new PedidoBean(0, listaFilho1.get(1), listaFilho2.get(1), listaFilho2.get(2), listaFilho3.get(1)
					, 2, new Date(), 10, 15, 20, 5, new Date(), 5, "N�o cr�tico"));
			
			GenericDAO<ProdutoAdicionalBean> adicionalDao = new GenericDAO<ProdutoAdicionalBean>();
			ArrayList<ProdutoAdicionalBean> adicionalLista 	= null;
			try {
				adicionalLista = adicionalDao.findLike(ProdutoAdicionalBean.class, new ProdutoAdicionalBean(0, "", 0));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ERRO FIND LIKE !!!!!!!!!!!!!!! = " + e.getMessage());
			}
			
			ArrayList<ItemPedidoAdicionalBean> itemPedidoAdicionalLista = new ArrayList<ItemPedidoAdicionalBean>();
			for (int i = 0; i < 3 ; ++i) {
				itemPedidoAdicionalLista.add(new ItemPedidoAdicionalBean(null, adicionalLista.get(i)));
			}
			
			GenericDAO<ProdutoBean> produtoDao = new GenericDAO<ProdutoBean>();
			ArrayList<ProdutoBean> produtoLista 	= null;
			try {
				produtoLista = produtoDao.findLike(ProdutoBean.class, new ProdutoBean(0, null, "", "", false));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ERRO FIND LIKE !!!!!!!!!!!!!!! = " + e.getMessage());
			}
			
			ArrayList<ItemPedidoBean> itemPedidoLista = new ArrayList<ItemPedidoBean>();
			for (int i = 0; i < 3 ; ++i) {
				itemPedidoLista.add(new ItemPedidoBean(0, null, produtoLista.get(i), 1, 5 ,1));
				if (i == 0 || i == 1) {
					itemPedidoLista.get(i).addAdicional(itemPedidoAdicionalLista.get(0));
				} else {
					itemPedidoLista.get(i).addAdicional(itemPedidoAdicionalLista.get(1));
					itemPedidoLista.get(i).addAdicional(itemPedidoAdicionalLista.get(2));
				}
			}
			
			lista.get(0).addItem(itemPedidoLista.get(0));
			lista.get(0).addItem(itemPedidoLista.get(1));
			lista.get(0).addItem(itemPedidoLista.get(2));
			
			lista.get(1).addItem(new ItemPedidoBean(
					itemPedidoLista.get(2).getCdItemPedido(),
					itemPedidoLista.get(2).getPedido(),
					itemPedidoLista.get(2).getProduto(),
					itemPedidoLista.get(2).getQtUnitaria(),
					itemPedidoLista.get(2).getVlUnitario(),
					itemPedidoLista.get(2).getVlDesconto()));
			
			System.out.println(lista);
			for (PedidoBean bean : lista) {
				try {
					System.out.println(bean);
					dao.save(bean, 0);
					
					for (ItemPedidoBean itemPedidoBean : bean.getItens()) {
						
						GenericDAO<ItemPedidoBean> ipDao = new GenericDAO<ItemPedidoBean>();	
						itemPedidoBean.setPedido(bean);
						ipDao.save(itemPedidoBean, 0);
						
						GenericDAO<ItemPedidoAdicionalBean> adDao = new GenericDAO<ItemPedidoAdicionalBean>();						
						for (ItemPedidoAdicionalBean itemAdicional : itemPedidoBean.getAdicionais()) {
							
							itemAdicional.setItemPedido(itemPedidoBean);
							adDao.save(itemAdicional, 0);
						}
					}
					
					
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO INSER��O !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
			
			for (int i = 1; i <= 2 ; ++i) {
				try {
					PedidoBean bean = dao.findById(PedidoBean.class, i);
					bean.setDsObservacao(bean.getDsObservacao() + " - Altera��o");
					
					if (i == 1)
						bean.getItens().get(0).setVlUnitario(100);
					
					dao.save(bean, bean.getCdPedido());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO BUSCA E ALTERA��O !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
			
			for (int i = 1; i <= 2 ; ++i) {
				try {
					PedidoBean bean = dao.findById(PedidoBean.class, i);
					System.out.println("ID: " + bean.getCdPedido() + ". DS: " + bean.getDsObservacao());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERRO BUSCA !!!!!!!!!!!!!!! = " + e.getMessage());
				}
			}
		}
		
		
		
		/*GenericDAO<ClienteBean> clienteDao = new GenericDAO<ClienteBean>();
		GenericDAO<FuncionarioBean> funcionarioDao = new GenericDAO<FuncionarioBean>();
		ClienteBean cliente1 = new ClienteBean(0, "Rafael Pegoretti", "(47)3336-8011", "(47)9 9109-9998", true, 0, "Testando Cliente 1");
		ClienteBean cliente2 = new ClienteBean(0, "Lino Pegoretti", "(47)3336-8011", "(47)9 9109-9998", true, 0, "Testando a insercao");
		FuncionarioBean funcionario1 = new FuncionarioBean(0, "Cristina Pegoretti", "(47)3336-8011", "(47)9 9115-9790",
				new Date(), true, 0, "Presidente", new Date(), "cristina", "123", 3);
		FuncionarioBean funcionario2 = new FuncionarioBean(0, "tutica", "(47)", "(47)", new Date(), true, 0, "Zelador",
				new Date(), "tutica", "123", 2);
		FuncionarioBean funcionario3 = new FuncionarioBean(0, "preta", "(47)", "(47)9", new Date(), true, 0, "Motoboy",
				new Date(), "preta", "123", 1);

		System.out.println(cliente1);
		try {
			System.out.println(clienteDao.save(cliente1, cliente1.getCdCliente()));
		} catch (BDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(cliente2);
		try {
			System.out.println(clienteDao.save(cliente2, cliente2.getCdCliente()));
		} catch (BDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(funcionario1);
		try {
			funcionarioDao.save(funcionario1, funcionario1.getCdFuncionario());
		} catch (BDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(funcionario2);
		try {
			funcionarioDao.save(funcionario2, funcionario2.getCdFuncionario());
		} catch (BDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(funcionario3);
		try {
			funcionarioDao.save(funcionario3, funcionario3.getCdFuncionario());
		} catch (BDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		FuncionarioBean funcionario = funcionarioDao.findById(FuncionarioBean.class, 3);
		System.out.println("Funcionario retornado do banco" + funcionario);

		List<ClienteBean> listaCliente = clienteDao.findLike(ClienteBean.class, cliente1);
		System.out.println(listaCliente);

		List<FuncionarioBean> listaFuncionario = funcionarioDao.findLike(FuncionarioBean.class, funcionario2);
		System.out.println(listaFuncionario);
		*/
		
	}

}
