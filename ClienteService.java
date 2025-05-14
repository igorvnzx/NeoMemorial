
package service;

import model.Cliente;

public class ClienteService {
    public void cadastrarCliente(Cliente cliente) {
        System.out.println("Cliente cadastrado: " + cliente.getNome());
    }
}
