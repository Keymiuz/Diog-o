import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Banco {

    private double saldo;
    private double limiteSaque;
    private List<String> historico;

    public Banco() {
        this.saldo = 0.0;
        this.limiteSaque = 1000.0;  // Limite padrão para saque
        this.historico = new ArrayList<>();
    }

    // Método para adicionar saldo
    public void adicionarSaldo(double valor) {
        if (valor > 0) {
            saldo += valor;
            historico.add("Adicionado: R$ " + valor);
            System.out.println("\033[0;32mSaldo adicionado com sucesso! Novo saldo: R$ " + saldo + "\033[0m");
        } else {
            System.out.println("\033[0;31mValor inválido para adicionar.\033[0m");
        }
    }

    // Método para sacar saldo
    public void sacarSaldo(double valor) {
        if (valor > 0 && valor <= saldo && valor <= limiteSaque) {
            saldo -= valor;
            historico.add("Saque: R$ " + valor);
            System.out.println("\033[0;32mSaque realizado com sucesso! Novo saldo: R$ " + saldo + "\033[0m");
        } else if (valor > saldo) {
            System.out.println("\033[0;31mSaldo insuficiente para realizar o saque.\033[0m");
        } else if (valor > limiteSaque) {
            System.out.println("\033[0;31mValor de saque excede o limite de R$ " + limiteSaque + ".\033[0m");
        } else {
            System.out.println("\033[0;31mValor inválido para saque.\033[0m");
        }
    }

    // Método para conferir o saldo
    public void conferirSaldo() {
        System.out.println("\033[0;34mSeu saldo atual é: R$ " + saldo + "\033[0m");
    }

    // Método para transferir saldo
    public void transferirSaldo(Banco destino, double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            destino.adicionarSaldo(valor);
            historico.add("Transferido: R$ " + valor);
            System.out.println("\033[0;32mTransferência de R$ " + valor + " realizada com sucesso!\033[0m");
        } else if (valor > saldo) {
            System.out.println("\033[0;31mSaldo insuficiente para transferir.\033[0m");
        } else {
            System.out.println("\033[0;31mValor inválido para transferência.\033[0m");
        }
    }

    // Método para visualizar o histórico de transações
    public void visualizarHistorico() {
        System.out.println("\033[0;33m### Histórico de Transações ###\033[0m");
        if (historico.isEmpty()) {
            System.out.println("\033[0;33mNenhuma transação registrada.\033[0m");
        } else {
            for (String transacao : historico) {
                System.out.println("\033[0;36m" + transacao + "\033[0m");
            }
        }
    }

    // Método para configurar limite de saque
    public void configurarLimiteSaque(double valor) {
        if (valor > 0) {
            limiteSaque = valor;
            System.out.println("\033[0;32mLimite de saque ajustado para: R$ " + limiteSaque + "\033[0m");
        } else {
            System.out.println("\033[0;31mValor inválido para o limite de saque.\033[0m");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Banco conta1 = new Banco();
        Banco conta2 = new Banco();
        int opcao;

        do {
            System.out.println("\n\033[0;35m### Banco Virtual ###\033[0m");
            System.out.println("\033[0;36m1. Adicionar Saldo");
            System.out.println("2. Sacar Saldo");
            System.out.println("3. Conferir Saldo");
            System.out.println("4. Transferir Saldo");
            System.out.println("5. Visualizar Histórico de Transações");
            System.out.println("6. Configurar Limite de Saque");
            System.out.println("7. Sair\033[0m");
            System.out.print("\033[0;33mEscolha uma opção: \033[0m");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("\033[0;33mDigite o valor a adicionar: R$ \033[0m");
                    double valorAdicionar = scanner.nextDouble();
                    conta1.adicionarSaldo(valorAdicionar);
                    break;
                case 2:
                    System.out.print("\033[0;33mDigite o valor a sacar: R$ \033[0m");
                    double valorSacar = scanner.nextDouble();
                    conta1.sacarSaldo(valorSacar);
                    break;
                case 3:
                    conta1.conferirSaldo();
                    break;
                case 4:
                    System.out.print("\033[0;33mDigite o valor a transferir para a outra conta: R$ \033[0m");
                    double valorTransferir = scanner.nextDouble();
                    conta1.transferirSaldo(conta2, valorTransferir);
                    break;
                case 5:
                    conta1.visualizarHistorico();
                    break;
                case 6:
                    System.out.print("\033[0;33mDigite o novo limite de saque: R$ \033[0m");
                    double limite = scanner.nextDouble();
                    conta1.configurarLimiteSaque(limite);
                    break;
                case 7:
                    System.out.println("\033[0;32mSaindo... Até logo!\033[0m");
                    break;
                default:
                    System.out.println("\033[0;31mOpção inválida, tente novamente.\033[0m");
            }
        } while (opcao != 7);

        scanner.close();
    }
}
