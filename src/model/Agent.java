package model;

public class Agent extends Person {
	/**
	 * agentType	[int]		: Identifiant du type de l'agent
	 * typeAgent	[TypeAgent]	: Type de l'agent 
	 * agentLogin	[String]	: Login
	 * agentPwd		[String]	: Mot de passe
	 * agentImage	[String]	: Nom du fichier image de l'agent
	 */
	private int    		agentType;
	private TypeAgent	typeAgent;
	private String 		agentLogin;
	private String 		agentPwd;
	private String 		agentImage;
	/**
	 * @param newPersonIdt
	 * @param newPersonName
	 * @param newPersonFirstName
	 * @param newPersonMobile
	 * @param newPersonPhone
	 * @param newPersonEmail
	 * @param newPersonCivility
	 * @param newCivility
	 * @param newAgentType
	 * @param newTypeAgent
	 * @param newAgentLogin
	 * @param newAgentPwd
	 * @param newAgentImage
	 */
	public Agent(int newPersonIdt, String newPersonName, String newPersonFirstName, String newPersonMobile,
			String newPersonPhone, String newPersonEmail, int newPersonCivility, Civility newCivility, int newAgentType, 
			TypeAgent newTypeAgent,String newAgentLogin, String newAgentPwd, String newAgentImage) {
		super(newPersonIdt, newPersonName, newPersonFirstName, newPersonMobile, newPersonPhone, newPersonEmail,
				newPersonCivility, newCivility);
		this.agentType 	= newAgentType;
		this.typeAgent	= newTypeAgent;
		this.agentLogin = newAgentLogin;
		this.agentPwd 	= newAgentPwd;
		this.agentImage = newAgentImage;
	}
	
	
	

	/**
	 * @return the agentType
	 */
	public int getAgentType() {
		return agentType;
	}
	/**
	 * @param agentType the agentType to set
	 */
	public void setAgentType(int agentType) {
		this.agentType = agentType;
	}
	/**
	 * @return the typeAgent
	 */
	public TypeAgent getTypeAgent() {
		return typeAgent;
	}
	/**
	 * @param typeAgent the typeAgent to set
	 */
	public void setTypeAgent(TypeAgent typeAgent) {
		this.typeAgent = typeAgent;
	}
	/**
	 * @return the agentLogin
	 */
	public String getAgentLogin() {
		return agentLogin;
	}
	/**
	 * @param agentLogin the agentLogin to set
	 */
	public void setAgentLogin(String agentLogin) {
		this.agentLogin = agentLogin;
	}
	/**
	 * @return the agentPwd
	 */
	public String getAgentPwd() {
		return agentPwd;
	}
	/**
	 * @param agentPwd the agentPwd to set
	 */
	public void setAgentPwd(String agentPwd) {
		this.agentPwd = agentPwd;
	}
	/**
	 * @return the agentImage
	 */
	public String getAgentImage() {
		return agentImage;
	}
	/**
	 * @param agentImage the agentImage to set
	 */
	public void setAgentImage(String agentImage) {
		this.agentImage = agentImage;
	}
	@Override
	public String toString() {
		return "Agent [agentType=" + agentType + ", typeAgent=" + typeAgent + ", agentLogin=" + agentLogin
				+ ", agentPwd=" + agentPwd + ", agentImage=" + agentImage + "]";
	}
	
	
	
	
	
}
