<?php

namespace RestoNurseryBundle\Controller;

use Dompdf\Dompdf;
use Dompdf\Options;
use PHPMailer\PHPMailer\Exception;
use PHPMailer\PHPMailer\PHPMailer;
use RestoNurseryBundle\Entity\SaveNurse;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;use Symfony\Component\HttpFoundation\Request;

/**
 * Savenurse controller.
 *
 * @Route("savenurse")
 */
class SaveNurseController extends Controller
{

    /**
     * Lists all saveNurse entities.
     *
     * @Route("/", name="savenurse_index")
     * @Method("GET")
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $saveNurses = $em->getRepository('RestoNurseryBundle:SaveNurse')->findAll();

        return $this->render('savenurse/index.html.twig', array(
            'saveNurses' => $saveNurses,
        ));
    }

    /**
     * Lists all saveNurse entities.
     *
     * @Route("/", name="savenurse_indexF")
     * @Method("GET")
     */
    public function indexFAction()
    {
        $em = $this->getDoctrine()->getManager();

        $saveNurses = $em->getRepository('RestoNurseryBundle:SaveNurse')->findAll();
        $mail = new PHPMailer(true);

        try {
            //Server settings
            //$mail->SMTPDebug = 1;                      // Enable verbose debug output
            $mail->isSMTP();                                            // Send using SMTP
            $mail->Host       = 'smtp.gmail.com';                    // Set the SMTP server to send through
            $mail->SMTPAuth   = true;                                   // Enable SMTP authentication
            // SMTP password
            $mail->SMTPSecure = 'tls';         // Enable TLS encryption; `PHPMailer::ENCRYPTION_SMTPS` also accepted
            $mail->Port       = 587;                                    // TCP port to connect to
            /* Username (email address). */
            $mail->Username = 'kindogarten2020@gmail.com';

            /* Google account password. */
            $mail->Password = '2020kindo';
            //Recipients
            $mail->setFrom('from@example.com', 'OUR POLICY ');
            $mail->addAddress('nada.chniter@esprit.tn');     // Add a recipient

            $mail->addAttachment('policy.pdf', 'policy.pdf');         // Add attachments

            // Content
            $mail->isHTML(true);                                  // Set email format to HTML
            $mail->Subject = 'POLICY';
             $mail->Body    = ' Good morning , This PDF contains the POLICY of our Website & Kindergarten. Cordially. <b>!</b> ';
             $mail->AltBody = 'This is the body in plain text for non-HTML mail clients';

            $mail->send();
          //  echo 'Message has been sent';
        } catch (Exception $e) {
         //   echo "Message could not be sent. Mailer Error: {$mail->ErrorInfo}";
        }


        return $this->render('savenurse/indexF.html.twig', array(
            'saveNurses' => $saveNurses,
        ));
    }
    /**
     * Creates a new saveNurse entity.
     *
     * @Route("/new", name="savenurse_new")
     * @Method({"GET", "POST"})
     */
    public function newAction(Request $request)
    {
        $saveNurse = new Savenurse();
        $form = $this->createForm('RestoNurseryBundle\Form\SaveNurseType', $saveNurse);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($saveNurse);
            $em->flush();

            return $this->redirectToRoute('savenurse_indexF', array('id' => $saveNurse->getId()));
        }

        return $this->render('savenurse/new.html.twig', array(
            'saveNurse' => $saveNurse,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a saveNurse entity.
     *
     * @Route("/{id}", name="savenurse_show")
     * @Method("GET")
     */
    public function showAction(SaveNurse $saveNurse)
    {
        $deleteForm = $this->createDeleteForm($saveNurse);

        return $this->render('savenurse/show.html.twig', array(
            'saveNurse' => $saveNurse,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Finds and displays a saveNurse entity.
     *
     * @Route("/{id}", name="savenurse_showF")
     * @Method("GET")
     */
    public function showFAction(SaveNurse $saveNurse)
    {
        $deleteForm = $this->createDeleteForm($saveNurse);

        return $this->render('savenurse/showF.html.twig', array(
            'saveNurse' => $saveNurse,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing saveNurse entity.
     *
     * @Route("/{id}/edit", name="savenurse_edit")
     * @Method({"GET", "POST"})
     */
    public function editAction(Request $request, SaveNurse $saveNurse)
    {
        $deleteForm = $this->createDeleteForm($saveNurse);
        $editForm = $this->createForm('RestoNurseryBundle\Form\SaveNurseType', $saveNurse);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('savenurse_edit', array('id' => $saveNurse->getId()));
        }

        return $this->render('savenurse/edit.html.twig', array(
            'saveNurse' => $saveNurse,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing saveNurse entity.
     *
     * @Route("/{id}/edit", name="savenurse_edit")
     * @Method({"GET", "POST"})
     */
    public function editFAction(Request $request, SaveNurse $saveNurse)
    {
        $deleteForm = $this->createDeleteForm($saveNurse);
        $editForm = $this->createForm('RestoNurseryBundle\Form\SaveNurseType', $saveNurse);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('savenurse_edit', array('id' => $saveNurse->getId()));
        }

        return $this->render('savenurse/editF.html.twig', array(
            'saveNurse' => $saveNurse,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }
    /**
     * Deletes a saveNurse entity.
     *
     * @Route("/{id}", name="savenurse_delete")
     * @Method("DELETE")
     */
    public function deleteAction(Request $request, SaveNurse $saveNurse)
    {
        $form = $this->createDeleteForm($saveNurse);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($saveNurse);
            $em->flush();
        }

        return $this->redirectToRoute('savenurse_indexF');
    }

    /**
     * Creates a form to delete a saveNurse entity.
     *
     * @param SaveNurse $saveNurse The saveNurse entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(SaveNurse $saveNurse)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('savenurse_delete', array('id' => $saveNurse->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }


}
