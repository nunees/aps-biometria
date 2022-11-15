package utils;

public enum TipoLog {
        APPLICATION(0), INFO(1), ALERTA(2), ERRO(3);
        private final int valor;
        TipoLog(int valorOpcao){
            valor = valorOpcao;
        }

        public int getValor(){
            return valor;
        }
    }

