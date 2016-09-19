package org.elsys.equalsAndHashCode;

public class Name {
	private String first;
	private String family;
	
	public Name(String first, String family) {
		this.first = first;
		this.family = family;
	}
	
	@Override
	public int hashCode() {
		return (first != null ? first.hashCode() : 0) 
				+ 31 * (family != null ? family.hashCode() : 0);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Name))
			return false;
		Name other = (Name) obj;
		if (family == null) {
			if (other.family != null)
				return false;
		} else if (!family.equals(other.family))
			return false;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		return true;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}
}
