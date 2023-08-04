package entity;





public class User {


		private Integer id;
		private String identifier;
		private String versions;
		private Integer count;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getVersions() {
		return versions;
	}

	public void setVersions(String versions) {
		this.versions = versions;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "User{" +
			"id=" + id +
			", identifier=" + identifier +
			", versions=" + versions +
			", count=" + count +
			"}";
	}
}
