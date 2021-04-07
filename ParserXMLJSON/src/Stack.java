public class Stack {
    private State topo = null;
    private int quantidade;

    public int Tamanho()
    {
        return quantidade;
    }
            
    public void empilha(String valor)
    {
        State Estado = new State();
        Estado.setValue(valor);
        Estado.setAnterior(topo);
        topo = Estado;
        quantidade++;
    }

    public String desempilha()
    {
        if (Tamanho() == 0)
        {
        	return "";
        }
        else
        {
            String retorno = topo.getValue();
            topo = topo.getAnterior();
            quantidade--;
            return retorno;
        }
    }

    public State retornaTopo()
    {
        if (Tamanho() == 0)
            return null;
        else
            return topo;
    }
}
