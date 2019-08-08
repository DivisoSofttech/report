import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ReportSharedModule } from 'app/shared';
import {
    OrderMasterComponent,
    OrderMasterDetailComponent,
    OrderMasterUpdateComponent,
    OrderMasterDeletePopupComponent,
    OrderMasterDeleteDialogComponent,
    orderMasterRoute,
    orderMasterPopupRoute
} from './';

const ENTITY_STATES = [...orderMasterRoute, ...orderMasterPopupRoute];

@NgModule({
    imports: [ReportSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        OrderMasterComponent,
        OrderMasterDetailComponent,
        OrderMasterUpdateComponent,
        OrderMasterDeleteDialogComponent,
        OrderMasterDeletePopupComponent
    ],
    entryComponents: [OrderMasterComponent, OrderMasterUpdateComponent, OrderMasterDeleteDialogComponent, OrderMasterDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ReportOrderMasterModule {}
