package ebkus.model;

import java.sql.Timestamp;

/**
 *
 * @author User
 */
public class BaseModel implements java.io.Serializable {

    private Integer idEntry;
    private Timestamp tglEntry;
    private Integer idEdit;
    private Timestamp tglEdit;

    /**
     * @return the idEntry
     */
    public Integer getIdEntry() {
        return idEntry;
    }

    /**
     * @param idEntry the idEntry to set
     */
    public void setIdEntry(Integer idEntry) {
        this.idEntry = idEntry;
    }

    /**
     * @return the tglEntry
     */
    public Timestamp getTglEntry() {
        return tglEntry;
    }

    /**
     * @param tglEntry the tglEntry to set
     */
    public void setTglEntry(Timestamp tglEntry) {
        this.tglEntry = tglEntry;
    }

    /**
     * @return the idEdit
     */
    public Integer getIdEdit() {
        return idEdit;
    }

    /**
     * @param idEdit the idEdit to set
     */
    public void setIdEdit(Integer idEdit) {
        this.idEdit = idEdit;
    }

    /**
     * @return the tglEdit
     */
    public Timestamp getTglEdit() {
        return tglEdit;
    }

    /**
     * @param tglEdit the tglEdit to set
     */
    public void setTglEdit(Timestamp tglEdit) {
        this.tglEdit = tglEdit;
    }
}
