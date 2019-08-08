import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IOrderMaster } from 'app/shared/model/report/order-master.model';

type EntityResponseType = HttpResponse<IOrderMaster>;
type EntityArrayResponseType = HttpResponse<IOrderMaster[]>;

@Injectable({ providedIn: 'root' })
export class OrderMasterService {
    public resourceUrl = SERVER_API_URL + 'api/order-masters';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/order-masters';

    constructor(private http: HttpClient) {}

    create(orderMaster: IOrderMaster): Observable<EntityResponseType> {
        return this.http.post<IOrderMaster>(this.resourceUrl, orderMaster, { observe: 'response' });
    }

    update(orderMaster: IOrderMaster): Observable<EntityResponseType> {
        return this.http.put<IOrderMaster>(this.resourceUrl, orderMaster, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IOrderMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IOrderMaster[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IOrderMaster[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }
}
