package constructionsite;

import estgconstroi.ConstructionSite;
import estgconstroi.ConstructionSiteManager;
import estgconstroi.Equipment;
import estgconstroi.Team;
import estgconstroi.exceptions.ConstructionSiteManagerException;

/**
 * Nome: Ricardo Afonso Saavedra Ferreira
 * Número: 8220132
 * Turma: LSIRC
 */

public class ConstructionSiteManagerImpl implements ConstructionSiteManager {
    private static final int INITIAL_CONSTRUCTION_SITES = 10;
    private ConstructionSite[] constructionSites;
    private int constructionSitesCount;

    public ConstructionSiteManagerImpl() {
        constructionSites = new ConstructionSiteImpl[INITIAL_CONSTRUCTION_SITES];
        constructionSitesCount = 0;
    }

    @Override
    public void add(ConstructionSite constructionSite) throws ConstructionSiteManagerException {
        if (constructionSite == null) throw new ConstructionSiteManagerException("Construction site is null");
        if (!constructionSite.isValid()) throw new ConstructionSiteManagerException("Construction site is invalid");
        for (int i = 0; i < constructionSitesCount; i++) {
            if (constructionSites[i].equals(constructionSite)) throw new ConstructionSiteManagerException("Construction site already added");
        }

        if (constructionSitesCount == constructionSites.length) increaseCountructionSitesArray();
        constructionSites[constructionSitesCount] = constructionSite;
        constructionSitesCount++;
    }

    @Override
    public Team[] getWorkingTeams() {
        return null;
    }

    @Override
    public Team[] getIddleTeams() {
        return null;
    }

    @Override
    public Equipment[] getEquipmentsInUse() {
        return null;
    }

    @Override
    public Equipment[] getIddleEquipments() {
        return null;
    }

    @Override
    public ConstructionSite[] getConstructionSitesWithPermitExpired() {
        return null;
    }

    @Override
    public boolean isValid() {
        return false;
    }

    private void increaseCountructionSitesArray() {
        ConstructionSite[] constructionSites = new ConstructionSiteImpl[this.constructionSites.length * 2];
        for (int i = 0; i < constructionSitesCount; i++) {
            constructionSites[i] = this.constructionSites[i];
        }

        this.constructionSites = constructionSites;
    }
}
