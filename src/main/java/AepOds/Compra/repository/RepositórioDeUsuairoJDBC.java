package AepOds.Compra.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import AepOds.Compra.ValueObject.DadosUsuario;

public class RepositórioDeUsuairoJDBC implements RepositórioDeUsuario {
        private Connection conexão;
        

        public RepositórioDeUsuairoJDBC(Connection conexão){
            this.conexão = conexão;
        }
    
        @Override
        public List<DadosUsuario> obterTodos() {
            List<DadosUsuario> todos = new ArrayList<>();
            try (Statement select = conexão.createStatement();
                ResultSet resultado = select.executeQuery("select id_usuario, cpf_usuario from usuario")){
                while (resultado.next()) {
                    DadosUsuario recuperado = new DadosUsuario(
                        resultado.getString("id_usuario"),
                        resultado.getString("cpf_usuario"));
                    todos.add(recuperado);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return todos;
        }
    
        @Override
        public void close() throws Exception {
            // TODO Auto-generated method stub
    
        }
}
