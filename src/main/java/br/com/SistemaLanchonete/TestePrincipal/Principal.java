package br.com.SistemaLanchonete.TestePrincipal;

import java.util.Date;
import java.util.List;

import br.com.SistemaLanchonete.domain.CargoBean;
import br.com.SistemaLanchonete.domain.ClienteBean;
import br.com.SistemaLanchonete.domain.FuncionarioBean;
import br.com.SistemaLanchonete.services.BDException;
import br.com.SistemaLanchonete.services.GenericDAO;

public class Principal {

	public static void main(String[] args) {
		GenericDAO<ClienteBean> clienteDao = new GenericDAO<ClienteBean>();
		GenericDAO<FuncionarioBean> funcionarioDao = new GenericDAO<FuncionarioBean>();
		GenericDAO<CargoBean> cargoDao = new GenericDAO<CargoBean>();

		CargoBean cargo1 = new CargoBean(0, "Atendente");
		CargoBean cargo2 = new CargoBean(0, "Motoboy");
		CargoBean cargo3 = new CargoBean(0, "Adminsitrador");
		System.out.println(cargo1);
		try {
			System.out.println(cargoDao.save(cargo1, cargo1.getCdCargo()));
		} catch (BDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(cargo2);
		try {
			System.out.println(cargoDao.save(cargo2, cargo2.getCdCargo()));
		} catch (BDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(cargo3);
		try {
			System.out.println(cargoDao.save(cargo3, cargo3.getCdCargo()));
		} catch (BDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ClienteBean cliente1 = new ClienteBean(0, "Rafael Pegoretti", "(47)3336-8011", "(47)9 9109-9998", new Date(),
				true, 0, "Testando Cliente 1");
		ClienteBean cliente2 = new ClienteBean(0, "Lino Pegoretti", "(47)3336-8011", "(47)9 9109-9998", new Date(),
				true, 0, "Testando a insercao");
		FuncionarioBean funcionario1 = new FuncionarioBean(0, "Cristina Pegoretti", "(47)3336-8011", "(47)9 9115-9790",
				new Date(), true, 0, cargo1, new Date(), "cristina", "123", 3);
		FuncionarioBean funcionario2 = new FuncionarioBean(0, "tutica", "(47)", "(47)", new Date(), true, 0, cargo2,
				new Date(), "tutica", "123", 2);
		FuncionarioBean funcionario3 = new FuncionarioBean(0, "preta", "(47)", "(47)9", new Date(), true, 0, cargo3,
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
	}

}
