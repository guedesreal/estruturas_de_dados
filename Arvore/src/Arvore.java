
public class Arvore {

	private No raiz;

	private int totalElementos = 0;

	/**
	 * Insere o elemento
	 * 
	 * @param elemento
	 */
	public void add(Comparable elemento) {
		add(getRaiz(), elemento);
	}
	
	/**
	 * Insere o elemento
	 * 
	 * @param elemento
	 */
	public void add(No noh, Comparable elemento) {
		No no = new No(elemento);
		if (getRaiz() == null) {
			raiz = no;
			this.totalElementos++;
			return;
		}
		if (noh == null) {
			if (elemento.compareTo(getRaiz().elemento) < 0) {
				if (getRaiz().esquerda == null) {
					getRaiz().esquerda = no;
				} else {
					add(getRaiz().esquerda, elemento);
				}
				this.totalElementos++;
			} else if (elemento.compareTo(getRaiz().elemento) > 0) {
				if (getRaiz().direita == null) {
					getRaiz().direita = no;
				} else {
					add(getRaiz().direita, elemento);
				}
				this.totalElementos++;
			}
			return;
		}
		if (elemento.compareTo(noh.elemento) < 0) {
			if (noh.esquerda == null) {
				noh.esquerda = no;
			} else {
				add(noh.esquerda, elemento);
			}
			this.totalElementos++;
		} else if (elemento.compareTo(noh.elemento) > 0) {
			if (noh.direita == null) {
				noh.direita = no;
			} else {
				add(noh.direita, elemento);
			}
			this.totalElementos++;
		}

	}

	public No remover(Comparable elemento) {
		return remover(getRaiz(),elemento);
	}

	public No remover(No aux, Comparable elemento) {
		No p, r = null;
		if (aux==null) {
			return aux;
		}
		if (aux.elemento.compareTo(elemento) == 0) {//achei o elemento
			if (aux.esquerda == null && aux.direita == null) {
				return null;
			} else if (aux.esquerda == null) {
				return aux.direita;
			} else if (aux.direita == null) {
				return aux.esquerda;
			} else {
				p = aux.direita;
				while (p.esquerda != null) {
					r = p;
					p = p.esquerda;
				}
				aux.elemento = p.elemento;
				
				if (r!=null) {
					p = p.direita;
					r.esquerda = p;
				} else {
					aux.direita = null;
				}
				return aux;
			}
		} else if (aux.elemento.compareTo(elemento) < 0) {
			aux.direita = remover(aux.direita, elemento);
		} else {
			aux.esquerda = remover(aux.esquerda, elemento);
		}
		return aux;
	}

	public boolean localizar(No aux, Comparable elemento, boolean loc) {
        if (aux != null && loc == false) {
            if (aux.elemento.equals(elemento)) {
                loc = true;
            } else if (elemento.compareTo(aux.elemento)<0) {
                loc = localizar(aux.esquerda, elemento, loc);
            } else {
                loc = localizar(aux.direita, elemento, loc);
            }
        }
        return loc;
    }

	/**
	 * Verifica se existe Veiculo na posicao indicada.
	 * 
	 * @param index
	 * @return
	 */
	public boolean posicaoValida(Integer index) {
		return index >= 0 && index < this.totalElementos;
	}

	@Override
	public String toString() {
		String str = getRaiz().toString();
		return str;
	}

	public static String imprimir(No aux) {
		String retorno;
		retorno = "(";
		if (aux != null) {
			retorno += "C" + aux.elemento;
			retorno += imprimir(aux.esquerda);
			retorno += imprimir(aux.direita);
		}
		retorno += ")";
		return retorno;
	}

	public No getRaiz() {
		return raiz;
	}
}
