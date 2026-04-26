package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class AgentEnAgence {

	/**
	 * agentIdt				: identifiant de l'agent
	 * companyIdt			: identifiant de l'agence
	 * company				: Company (agence)
	 * agentIsAttached		: l'agent est rattaché à l'agence
	 */
	private int 			agentIdt;
	private Agent			agent;
	private int 			companyIdt;
	private Company			company;
	private boolean 		agentIsAttached;
	/**
	 * @param newAgentIdt
	 * @param newAgent
	 * @param newCompanyIdt
	 * @param newCompany
	 * @param newAgentIsAttached
	 */
	public AgentEnAgence(int newAgentIdt, Agent newAgent, int newCompanyIdt, Company newCompany, boolean newAgentIsAttached) {
		super();
		this.agentIdt 			= newAgentIdt;
		this.agent				= newAgent;
		this.companyIdt 		= newCompanyIdt;
		this.company	 		= newCompany;
		this.agentIsAttached 	= newAgentIsAttached;
	}
	public int getAgentIdt() {
		return agentIdt;
	}
	public int getCompanyIdt() {
		return companyIdt;
	}
	public boolean getAgentIsAttached() {
		return agentIsAttached;
	}
	public BooleanProperty getAgentIsAttachedProperty() {
		return new SimpleBooleanProperty(agentIsAttached);
	}	
	public Agent getAgent() {
		return agent;
	}
	public Company getCompany() {
		return company;
	}
	public void setAgentIdt(int agentIdt) {
		this.agentIdt = agentIdt;
	}
	public void setCompanyIdt(int companyIdt) {
		this.companyIdt = companyIdt;
	}
	public void setAgentIsAttached(Boolean agentIsAttached) {
		this.agentIsAttached = agentIsAttached;
	}
	@Override
	public String toString() {
		return "AgentEnAgence [agentIdt=" + agentIdt + ", companyIdt=" + companyIdt + ", agentIsAttached=" + agentIsAttached + "]";
	}
}