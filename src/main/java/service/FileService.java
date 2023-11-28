
/*    static String salvar(String nomeArquivo, byte[] arquivo) throws IOException 
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'salvar'");
    }
*/

package service;

import java.io.File;
import java.io.IOException;

public interface FileService {

    String salvar(String nomeArquivo, byte[] arquivo) throws IOException;

    File obter(String nomeArquivo); 

}