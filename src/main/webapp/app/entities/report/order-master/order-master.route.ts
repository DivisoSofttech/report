import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { OrderMaster } from 'app/shared/model/report/order-master.model';
import { OrderMasterService } from './order-master.service';
import { OrderMasterComponent } from './order-master.component';
import { OrderMasterDetailComponent } from './order-master-detail.component';
import { OrderMasterUpdateComponent } from './order-master-update.component';
import { OrderMasterDeletePopupComponent } from './order-master-delete-dialog.component';
import { IOrderMaster } from 'app/shared/model/report/order-master.model';

@Injectable({ providedIn: 'root' })
export class OrderMasterResolve implements Resolve<IOrderMaster> {
    constructor(private service: OrderMasterService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<OrderMaster> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<OrderMaster>) => response.ok),
                map((orderMaster: HttpResponse<OrderMaster>) => orderMaster.body)
            );
        }
        return of(new OrderMaster());
    }
}

export const orderMasterRoute: Routes = [
    {
        path: 'order-master',
        component: OrderMasterComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'reportApp.reportOrderMaster.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'order-master/:id/view',
        component: OrderMasterDetailComponent,
        resolve: {
            orderMaster: OrderMasterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'reportApp.reportOrderMaster.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'order-master/new',
        component: OrderMasterUpdateComponent,
        resolve: {
            orderMaster: OrderMasterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'reportApp.reportOrderMaster.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'order-master/:id/edit',
        component: OrderMasterUpdateComponent,
        resolve: {
            orderMaster: OrderMasterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'reportApp.reportOrderMaster.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const orderMasterPopupRoute: Routes = [
    {
        path: 'order-master/:id/delete',
        component: OrderMasterDeletePopupComponent,
        resolve: {
            orderMaster: OrderMasterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'reportApp.reportOrderMaster.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
