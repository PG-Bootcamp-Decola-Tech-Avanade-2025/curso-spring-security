package pg.decola_tech_avanade_2025.cursos.spring_security.security;

import java.util.Date;
import java.util.List;

public class JwtObject {
    private String subject;
    private Date issuedAt;
    private Date expiresAt;
    private List<String> roles;

    public void setRoles(String... roles) {
        this.roles = List.of(roles);
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(Date issuedAt) {
        this.issuedAt = issuedAt;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
