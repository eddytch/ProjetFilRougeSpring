package fr.iocean.application.repository;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Created by utilisateur on 27/04/2016.
 */
public class PageableImpl implements Pageable {

    private int currentPage = 0 ;
    private static final int  nbPerPage = 10 ;

    @Override
    public int getPageNumber() {
        return currentPage;
    }

    @Override
    public int getPageSize() {
        return nbPerPage;
    }

    @Override
    public int getOffset() {
        return currentPage*nbPerPage;
    }

    @Override
    public Sort getSort() {
        return null;
    }

    @Override
    public Pageable next() {
        PageableImpl pageable = null ;
        try {
            pageable = (PageableImpl) this.clone();
            pageable.currentPage++ ;
        }catch(CloneNotSupportedException e){

        }
        return pageable ;
    }

    @Override
    public Pageable previousOrFirst() {
        PageableImpl pageable = null ;
        try {
            pageable = (PageableImpl) this.clone();
            if(pageable.currentPage>0)
                pageable.currentPage-- ;
        }catch(CloneNotSupportedException e){

        }
        return pageable ;
    }

    @Override
    public Pageable first() {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }
}
