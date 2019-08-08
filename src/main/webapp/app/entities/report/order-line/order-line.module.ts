import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ReportSharedModule } from 'app/shared';
import {
    OrderLineComponent,
    OrderLineDetailComponent,
    OrderLineUpdateComponent,
    OrderLineDeletePopupComponent,
    OrderLineDeleteDialogComponent,
    orderLineRoute,
    orderLinePopupRoute
} from './';

const ENTITY_STATES = [...orderLineRoute, ...orderLinePopupRoute];

@NgModule({
    imports: [ReportSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        OrderLineComponent,
        OrderLineDetailComponent,
        OrderLineUpdateComponent,
        OrderLineDeleteDialogComponent,
        OrderLineDeletePopupComponent
    ],
    entryComponents: [OrderLineComponent, OrderLineUpdateComponent, OrderLineDeleteDialogComponent, OrderLineDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ReportOrderLineModule {}
