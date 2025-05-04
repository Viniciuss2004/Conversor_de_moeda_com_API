package ExceptionP;

public class OpcaoInvalidaException extends RuntimeException {
    private String mensagem;

    public OpcaoInvalidaException(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String getMessage() {
        return mensagem;
    }
}
