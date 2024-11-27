
import com.redesocial.gerenciador.GerenciadorUsuarios;
import com.redesocial.ui.MenuPrincipal;
import com.redesocial.ui.MenuUsuario;

public class Main {

    public static void main(String[] args) {
        GerenciadorUsuarios gerenciadorUsuarios = new GerenciadorUsuarios();

        MenuPrincipal menuPrincipal = new MenuPrincipal(gerenciadorUsuarios);

        menuPrincipal.exibirMenu();
    }
}
